package org.shortener.myurlshortener.controller

import org.shortener.myurlshortener.service.UrlShortenerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping("/api/urls")
class UrlShortenerController(private val urlShortenerService: UrlShortenerService) {

    @PostMapping("/shorten", produces = ["application/json;charset=UTF-8"])
    @ResponseStatus(HttpStatus.CREATED)
    fun shortenUrl(@RequestBody originalUrl: String): String {
        val shortUrl = urlShortenerService.shortenUrl(originalUrl)
        println("Generated shortcode: ${shortUrl.shortCode}")
        val shortCode = shortUrl.shortCode
        println(shortCode)
        return "Shortened URL: /api/urls/$shortCode\n"
    }

    @GetMapping("/{shortCode}")
    fun redirectToOriginalUrl(@PathVariable shortCode: String): RedirectView {
        val originalUrl = urlShortenerService.getOriginalUrl(shortCode)
        return if (originalUrl != null) {
            RedirectView(originalUrl)
        } else {
            RedirectView("")
        }
    }
}
