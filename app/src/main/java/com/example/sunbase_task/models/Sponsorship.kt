package com.example.sunbase_task.models

data class Sponsorship(
    val impression_urls: List<String>?,
    val sponsor: Sponsor?,
    val tagline: String?,
    val tagline_url: String?
)