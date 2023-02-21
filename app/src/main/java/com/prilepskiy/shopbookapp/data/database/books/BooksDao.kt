package com.prilepskiy.shopbookapp.data.database.books

import androidx.room.Dao
import androidx.room.Query
import com.prilepskiy.shopbookapp.data.database.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BooksDao: BaseDao<BookEntity>() {
    @Query("SELECT * FROM book_table")
    abstract fun getAllBook(): Flow<List<BookEntity>>
}