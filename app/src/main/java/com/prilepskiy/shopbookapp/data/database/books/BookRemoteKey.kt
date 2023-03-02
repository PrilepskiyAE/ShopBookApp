package com.prilepskiy.shopbookapp.data.database.books

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class BookRemoteKey (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "book_id")
    val bookID: Int,
    val prevKey: Int?,
    val currentPage: Int,
    val nextKey: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)
