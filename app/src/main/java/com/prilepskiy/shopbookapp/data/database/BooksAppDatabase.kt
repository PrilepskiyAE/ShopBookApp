package com.prilepskiy.shopbookapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase.Companion.VERSION_SCHEMA
import com.prilepskiy.shopbookapp.data.database.books.BookEntity
import com.prilepskiy.shopbookapp.data.database.books.BooksDao
import com.prilepskiy.shopbookapp.data.database.user.UserDao
import com.prilepskiy.shopbookapp.data.database.user.UserEntity

@Database(
    entities = [BookEntity::class, UserEntity::class] ,
    version = VERSION_SCHEMA,
    exportSchema = true
)
abstract class BooksAppDatabase : RoomDatabase(){
    abstract val booksDao: BooksDao
    abstract val userDao: UserDao
    companion object{
        const val VERSION_SCHEMA=1
    }
}