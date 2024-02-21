package org.shortener.myurlshortener.service

import org.shortener.myurlshortener.model.ShortUrl
import org.shortener.myurlshortener.repository.ShortUrlRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UrlShortenerService @Autowired constructor(
    private val shortUrlRepository: ShortUrlRepository
) {
    private fun generateShortCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        return (1..6)
            .map { chars[random.nextInt(chars.length)] }
            .joinToString("")
    }

    fun shortenUrl(originalUrl: String): ShortUrl {
        val shortCode = generateShortCode()
        val shortUrl = ShortUrl(originalUrl = originalUrl, shortCode = shortCode)
        return shortUrlRepository.save(shortUrl)
    }

    fun getOriginalUrl(shortCode: String): String? {
        val shortUrl = shortUrlRepository.findByShortCode(shortCode)
        return shortUrl?.originalUrl
    }
}