package com.prilepskiy.shopbookapp.data.database.user

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_table")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name:String
)