package com.prilepskiy.shopbookapp.domain.repository

import androidx.paging.PagingData
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getBooks(): Flow<PagingData<BookModel>>
   suspend fun getBooksDataBase(): Flow<List<BookModel>>
}