package com.prilepskiy.shopbookapp.core

data class CallException(
    val errorCode: Int,
    val errorMessage: String? = null,
) : Exception()