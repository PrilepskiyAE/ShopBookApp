package com.prilepskiy.shopbookapp.data.database.books

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.prilepskiy.shopbookapp.data.response.AuthorResponse
import com.prilepskiy.shopbookapp.data.response.BookResponse
import com.prilepskiy.shopbookapp.data.response.TranslatorResponse
import com.prilepskiy.shopbookapp.domain.model.BookModel

@Entity(tableName = "book_table")
@TypeConverters(BookEntityConverter::class)
data class BookEntity(
    val author: List<AuthorResponse?>,
    val bookshelves: List<String?>,
    val copyright: Boolean,
    val download_count: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val languages: List<String?>,
    val media_type: String,
    val subjects: List<String?>,
    val title: String,
    val translator: List<TranslatorResponse?>
) {
    companion object {
        fun from(data: BookResponse?): BookEntity {
            return BookEntity(
                data?.authors ?: listOf(),
                data?.bookshelves ?: listOf(),
                data?.copyright ?: false,
                data?.download_count ?: 0,
                data?.id ?: 0,
                data?.languages ?: listOf(),
                data?.media_type ?: "",
                data?.subjects ?: listOf(),
                data?.title ?: "",
                data?.translator ?: listOf()
            )
        }

        fun from(data: BookModel): BookEntity = with(data) {
            BookEntity(
                AuthorResponse.from(author),
                bookshelves,
                copyright,
                download_count,
                id,
                languages,
                media_type,
                subjects,
                title,
                TranslatorResponse.from(translator)
            )
        }
    }
}