package com.prilepskiy.shopbookapp.domain.usecase

import androidx.paging.PagingData
import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListNetworkUseCase
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookListNetworkUseCaseImpl@Inject constructor(private val repository: BookRepository):
    GetBookListNetworkUseCase {
    override fun invoke(): Flow<PagingData<BookModel>> = repository.getBooks()

}