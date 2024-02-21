package org.shortener.myurlshortener.model

import jakarta.persistence.*

@Entity
data class ShortUrl(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val originalUrl: String,

    @Column(nullable = false, unique = true)
    val shortCode: String
)