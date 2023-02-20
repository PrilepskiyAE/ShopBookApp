package com.prilepskiy.shopbookapp.data.database.user

import androidx.room.Dao
import com.prilepskiy.shopbookapp.data.database.BaseDao

@Dao
abstract class UserDao: BaseDao<UserEntity>() {
}