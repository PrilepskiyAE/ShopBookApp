package com.prilepskiy.shopbookapp.data.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<T>)

    @Update
    abstract suspend fun update(data: T)

    @Delete
    abstract suspend fun delete(data: T)


}