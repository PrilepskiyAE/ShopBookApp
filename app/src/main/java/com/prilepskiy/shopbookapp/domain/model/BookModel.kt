package com.prilepskiy.shopbookapp.domain.model

import com.prilepskiy.shopbookapp.data.database.books.BookEntity
import com.prilepskiy.shopbookapp.data.response.AuthorResponse
import com.prilepskiy.shopbookapp.data.response.BookResponse
import com.prilepskiy.shopbookapp.data.response.TranslatorResponse

data class BookModel(
    val author: List<AuthorModel>,
    val bookshelves: List<String?>,
    val copyright: Boolean,
    val download_count: Int,
    val id: Int,
    val languages: List<String?>,
    val media_type: String,
    val subjects: List<String?>,
    val title: String,
    val translator: List<TranslatorModel>
){
    companion object{
        fun from(data: BookResponse): BookModel = with(data) {
            BookModel(
                AuthorModel.from(authors),
                bookshelves?:listOf(),
                copyright?:false,
                download_count?:0,
                id?:0,
                languages,
                media_type?:"",
                subjects,
                title?:"",
                TranslatorModel.from(translator)
            )
        }
        fun from(data: BookEntity): BookModel =with(data) {
            BookModel(
                AuthorModel.from(author),
                bookshelves,
                copyright,
                download_count,
                id,
                languages,
                media_type,
                subjects,
                title,
                TranslatorModel.from(translator)
            )
        }
        fun from(data:List<BookEntity>):List<BookModel> {
            val temp:MutableList<BookModel> = mutableListOf()
            data.forEach {
                temp.add(from(it))
            }
            return temp
        }

    }
}