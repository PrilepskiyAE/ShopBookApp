package com.prilepskiy.shopbookapp.di

import android.content.Context
import androidx.room.Room
import com.prilepskiy.shopbookapp.data.apiservice.BookApiService
import com.prilepskiy.shopbookapp.data.database.BooksAppDatabase
import com.prilepskiy.shopbookapp.data.repository.BannersRepositoryImpl
import com.prilepskiy.shopbookapp.data.repository.BookRepositoryImpl
import com.prilepskiy.shopbookapp.data.unit.HeaderInterceptor
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListDataBaseUseCase
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListNetworkUseCase
import com.prilepskiy.shopbookapp.domain.interactors.GetListBannerUseCase
import com.prilepskiy.shopbookapp.domain.repository.BannersRepository
import com.prilepskiy.shopbookapp.domain.repository.BookRepository
import com.prilepskiy.shopbookapp.domain.usecase.GetBookListDataBaseUseCaseImpl
import com.prilepskiy.shopbookapp.domain.usecase.GetBookListNetworkUseCaseImpl
import com.prilepskiy.shopbookapp.domain.usecase.GetListBannerUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class BookAppDBModule {
    @Provides
    fun provideDB(@ApplicationContext context: Context): BooksAppDatabase = Room.databaseBuilder(
        context,
        BooksAppDatabase::class.java, "BookDB"
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitMovie(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://gutendex.com")
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                client(
                    OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .readTimeout(1, TimeUnit.MINUTES)
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .writeTimeout(1, TimeUnit.MINUTES)
                        .build()
                )
            }
            .build()

}

@Module
@InstallIn(SingletonComponent::class)
class BooksApiModule() {
    @Provides
    @Singleton
    fun provideApiModule(retrofit: Retrofit): BookApiService =
        retrofit.create(BookApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
class RepositoryBookModule() {
    @Provides
    @Singleton
    fun provideRepositoryBook(
        dataNetwork: BookApiService,
        database: BooksAppDatabase
    ): BookRepository = BookRepositoryImpl(dataNetwork, database)
}


@Module
@InstallIn(SingletonComponent::class)
class BannersRepositoryModule() {
    @Provides
    @Singleton
    fun provideBannersRepository(
        dataNetwork: BookApiService,
        database: BooksAppDatabase
    ): BannersRepository = BannersRepositoryImpl()
}

@Module
@InstallIn(ViewModelComponent::class)
class UseCasBookModule() {
    @Provides
    fun provideGetBookListDataBaseUseCase(data: BookRepository): GetBookListDataBaseUseCase =
        GetBookListDataBaseUseCaseImpl(data)

    @Provides
    fun provideGetBookListNetworkUseCase(data: BookRepository): GetBookListNetworkUseCase =
        GetBookListNetworkUseCaseImpl(data)

    @Provides
    fun provideGetListBannerUseCase(data: BannersRepository): GetListBannerUseCase =
        GetListBannerUseCaseImpl(data)

}
