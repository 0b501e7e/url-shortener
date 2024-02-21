package org.shortener.myurlshortener.repository

import org.shortener.myurlshortener.model.ShortUrl
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShortUrlRepository : JpaRepository<ShortUrl, Long> {
    fun findByShortCode(shortCode: String): ShortUrl?
}