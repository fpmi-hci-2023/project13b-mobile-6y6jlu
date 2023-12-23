package com.example.forwords.data

data class FullBookModel(
    val book_id: Int,
    val author: String,
    val name: String,
    val annotation: String,
    val rate: Float,
    val path: String
)
