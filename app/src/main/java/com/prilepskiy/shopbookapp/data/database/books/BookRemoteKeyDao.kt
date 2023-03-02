package com.prilepskiy.shopbookapp.data.database.books

import androidx.room.Dao
import androidx.room.Query
import com.prilepskiy.shopbookapp.data.database.BaseDao
import kotlinx.coroutines.flow.Flow

@Dao
abstract class BookRemoteKeyDao: BaseDao<BookRemoteKey>() {
    @Query("Select * From remote_key Where book_id = :id")
   abstract suspend fun getRemoteKeyByBookID(id: Int): BookRemoteKey?

    @Query("Delete From remote_key")
  abstract  suspend fun clearRemoteKeys()

    @Query("Select created_at From remote_key Order By created_at DESC LIMIT 1")
  abstract  suspend fun getCreationTime(): Long?
}