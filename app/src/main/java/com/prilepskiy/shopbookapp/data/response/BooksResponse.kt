package com.prilepskiy.shopbookapp.data.response

data class BooksResponse(
    val count: Int,
    val next: String,
    val results: List<BookResponse>
)