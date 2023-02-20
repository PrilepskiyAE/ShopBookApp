package com.prilepskiy.shopbookapp.data.database.books

import androidx.room.Dao
import com.prilepskiy.shopbookapp.data.database.BaseDao

@Dao
abstract class BooksDao: BaseDao<BookEntity>() {
}