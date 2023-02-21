package com.prilepskiy.shopbookapp.domain.usecase

import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.interactors.GetBookListNetworkUseCase
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookListNetworkUseCaseImpl@Inject constructor(private val repository: BookRepository):
    GetBookListNetworkUseCase {
    override suspend fun invoke(): ActionResult<List<BookModel>> = withContext(Dispatchers.IO) {
        repository.getBooks()
    }
}