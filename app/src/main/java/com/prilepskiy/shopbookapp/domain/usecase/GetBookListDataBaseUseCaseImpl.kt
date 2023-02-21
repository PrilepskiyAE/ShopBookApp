package com.prilepskiy.shopbookapp.domain.usecase

import com.prilepskiy.shopbookapp.domain.interactors.GetBookListDataBaseUseCase
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.domain.repository.BookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetBookListDataBaseUseCaseImpl @Inject constructor(private val repository:BookRepository):
    GetBookListDataBaseUseCase {
    override suspend fun invoke(): Flow<List<BookModel>> = withContext(Dispatchers.IO) {
        return@withContext repository.getBooksDataBase()
    }
}