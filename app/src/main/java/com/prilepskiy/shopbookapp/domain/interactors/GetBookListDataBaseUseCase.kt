package com.prilepskiy.shopbookapp.domain.interactors


import com.prilepskiy.shopbookapp.domain.model.BookModel
import kotlinx.coroutines.flow.Flow

interface GetBookListDataBaseUseCase {
    suspend operator fun invoke(): Flow<List<BookModel>>
}