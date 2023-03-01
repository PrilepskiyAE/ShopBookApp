package com.prilepskiy.shopbookapp.data.response

data class BookResponse(
    val authors: List<AuthorResponse?>,
    val bookshelves: List<String?>,
    val copyright: Boolean?,
    val download_count: Int?,
    val id: Int?,
    val languages: List<String?>,
    val media_type: String?,
    val subjects: List<String?>,
    val title: String?,
    val translator: List<TranslatorResponse?>
)