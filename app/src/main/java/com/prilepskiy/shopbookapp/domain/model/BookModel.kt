package com.prilepskiy.shopbookapp.domain.model

data class BookModel(
    val author: List<AuthorModel>,
    val bookshelves: List<String>,
    val copyright: Boolean,
    val download_count: Int,
    val id: Int,
    val languages: List<String>,
    val media_type: String,
    val subjects: List<String>,
    val title: String,
    val translator: List<TranslatorModel>
)