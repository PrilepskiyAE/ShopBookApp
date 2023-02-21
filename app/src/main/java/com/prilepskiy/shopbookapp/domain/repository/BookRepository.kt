package com.prilepskiy.shopbookapp.domain.repository

import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface BookRepository {
  suspend  fun getBooks():ActionResult<List<BookModel>>
   suspend fun getBooksDataBase(): Flow<List<BookModel>>
}