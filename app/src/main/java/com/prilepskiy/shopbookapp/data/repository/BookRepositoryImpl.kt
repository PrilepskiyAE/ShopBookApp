package com.prilepskiy.shopbookapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.data.apiservice.BookApiService
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase
import com.prilepskiy.shopbookapp.data.database.books.BookEntity
import com.prilepskiy.shopbookapp.data.pagingSource.BooksPagingSource
import com.prilepskiy.shopbookapp.data.unit.analyzeResponse
import com.prilepskiy.shopbookapp.data.unit.makeApiCall
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val dataNetwork: BookApiService,private val dataDB:BooksAppDatabase): BookRepository {

    override fun getBooks(): Flow<PagingData<BookModel>> {
       return Pager(config = PagingConfig(
           pageSize = 32,
       ), pagingSourceFactory ={ BooksPagingSource(dataNetwork,dataDB)} ).flow
    }

    override suspend fun getBooksDataBase(): Flow<List<BookModel>> = dataDB.booksDao.getAllBook().map {
        BookModel.from(it)
    }
}