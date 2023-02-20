package com.prilepskiy.shopbookapp.data.unit

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HeaderInterceptor() : Interceptor {
    lateinit var request: Request
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val requestBuilder = originalRequest.newBuilder()
            .method(originalRequest.method, originalRequest.body)
        request = requestBuilder.build()

        return chain.proceed(request)
    }
}