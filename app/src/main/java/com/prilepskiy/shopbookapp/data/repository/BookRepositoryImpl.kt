package com.prilepskiy.shopbookapp.data.repository

import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.data.apiservice.BookApiService
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase
import com.prilepskiy.shopbookapp.data.database.books.BookEntity
import com.prilepskiy.shopbookapp.data.unit.analyzeResponse
import com.prilepskiy.shopbookapp.data.unit.makeApiCall
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(private val dataNetwork: BookApiService,private val dataDB:BooksAppDatabase): BookRepository {

    override suspend fun getBooks(): ActionResult<List<BookModel>> {
        val resultData:MutableList<BookModel> = mutableListOf()
        val apiData = makeApiCall {
            analyzeResponse(
                dataNetwork.getBooks()
            )
        }
        return when(apiData){
            is ActionResult.Success -> {
                apiData.data.results.onEach {
                    resultData.add(BookModel.from(it))
                    dataDB.booksDao.insert(BookEntity.from(it))
                }
                ActionResult.Success(resultData)
            }
            is ActionResult.Error -> {
                ActionResult.Error(apiData.errors)
            }
        }
    }

    override suspend fun getBooksDataBase(): Flow<List<BookModel>> = dataDB.booksDao.getAllBook().map {
        BookModel.from(it)
    }
}