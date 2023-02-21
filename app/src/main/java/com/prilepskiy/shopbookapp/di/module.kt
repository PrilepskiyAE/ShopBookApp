package com.prilepskiy.shopbookapp.di

import android.content.Context
import androidx.room.Room
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class BookAppDBModule {
    @Provides
    fun provideDB(@ApplicationContext context: Context): BooksAppDatabase = Room.databaseBuilder(
        context,
        BooksAppDatabase::class.java, "BookDB"
    ).build()
}