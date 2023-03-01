package com.prilepskiy.shopbookapp.data.apiservice

import com.prilepskiy.shopbookapp.data.response.BooksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApiService {
    @GET("books")
    suspend fun getBooks(
        @Query("page") page: Int
    ): Response<BooksResponse>
}