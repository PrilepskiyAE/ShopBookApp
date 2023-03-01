package com.prilepskiy.shopbookapp.domain.interactors


import androidx.paging.PagingData
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface GetBookListNetworkUseCase {
     operator fun invoke(): Flow<PagingData<BookModel>>
}