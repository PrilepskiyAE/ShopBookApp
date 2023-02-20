package com.prilepskiy.shopbookapp.data.apiservice

import com.prilepskiy.shopbookapp.data.response.BooksResponse
import retrofit2.Response
import retrofit2.http.GET

interface BookApiService {
    @GET("books")
    suspend fun getBooks(): Response<BooksResponse>
}