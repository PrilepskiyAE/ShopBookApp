package com.prilepskiy.shopbookapp.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.core.data
import com.prilepskiy.shopbookapp.core.succeeded
import com.prilepskiy.shopbookapp.data.apiservice.BookApiService
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase
import com.prilepskiy.shopbookapp.data.database.books.BookEntity
import com.prilepskiy.shopbookapp.data.response.BookResponse
import com.prilepskiy.shopbookapp.data.response.BooksResponse
import com.prilepskiy.shopbookapp.data.unit.analyzeResponse
import com.prilepskiy.shopbookapp.data.unit.makeApiCall
import com.prilepskiy.shopbookapp.domain.model.BookModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BooksPagingSource (
    private val booksApiService: BookApiService,private val dataDB: BooksAppDatabase
): PagingSource<Int, BookModel>() {
    override fun getRefreshKey(state: PagingState<Int, BookModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookModel> {
        return try {
            withContext(Dispatchers.IO) {
                val page = params.key ?: 1
                val result=getDataFromSource(page)
                if (result.succeeded) {

                    LoadResult.Page(data = result.data!!,
                        prevKey = if (page == 1) null else page.minus(1),
                        nextKey = if (result.data.isNullOrEmpty()) null else page.plus(1)
                        )
                }else{
                    LoadResult.Error((result as ActionResult.Error).errors)
                }


        }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    suspend fun getDataFromSource(page:Int): ActionResult<List<BookModel>> {
        val resultData:MutableList<BookModel> = mutableListOf()
        val apiData = makeApiCall {
            analyzeResponse(
                booksApiService.getBooks(page)
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
              // val cash=dataDB.booksDao.getAllBook()
               ActionResult.Error(apiData.errors)

            }
        }
    }
}
