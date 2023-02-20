package com.prilepskiy.shopbookapp.data.database

import androidx.room.RoomDatabase

abstract class BooksAppDatabase : RoomDatabase(){
    companion object{
        const val VERSION_SCHEMA=1
    }
}