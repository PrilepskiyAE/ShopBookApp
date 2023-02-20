package com.prilepskiy.shopbookapp.data.unit

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun getUniqueId(): Long {
    runBlocking {
        delay(1L)
    }
    return System.currentTimeMillis()
}