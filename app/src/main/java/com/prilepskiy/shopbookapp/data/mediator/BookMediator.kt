package com.prilepskiy.shopbookapp.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState.Loading.endOfPaginationReached
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.prilepskiy.shopbookapp.data.apiservice.BookApiService
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase
import com.prilepskiy.shopbookapp.data.database.books.BookEntity
import com.prilepskiy.shopbookapp.data.database.books.BookRemoteKey
import com.prilepskiy.shopbookapp.domain.model.BookModel
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class BookMediator(private val booksApiService: BookApiService,private val dataDB: BooksAppDatabase):
    RemoteMediator<Int, BookEntity>()
{

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (dataDB.remoteKey.getCreationTime() ?: 0) < cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }


        try {
            val apiResponse =booksApiService.getBooks(page)
            val books = apiResponse.body()?.results
            val endOfPaginationReached = books.isNullOrEmpty()
            dataDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    dataDB.remoteKey.clearRemoteKeys()
                    dataDB.booksDao.clearAllBook()
                }
                val prevKey = if (page == 1) null else page.minus(1)
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = books?.map {
                    BookRemoteKey(bookID = it.id?:1, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }
                if (remoteKeys != null) {
                    dataDB.remoteKey.insert(remoteKeys)
                }
                books?.forEach {
                    dataDB.booksDao.insert(BookEntity.from(it,page))
                }

            }


            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, BookEntity>): BookRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                dataDB.remoteKey.getRemoteKeyByBookID(id)
            }
        }
    }
    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, BookEntity>): BookRemoteKey? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            dataDB.remoteKey.getRemoteKeyByBookID(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int,BookEntity>): BookRemoteKey? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            dataDB.remoteKey.getRemoteKeyByBookID(movie.id)
        }
    }

}

