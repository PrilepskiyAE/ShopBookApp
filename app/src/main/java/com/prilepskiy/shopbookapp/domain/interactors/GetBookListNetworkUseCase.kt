package com.prilepskiy.shopbookapp.domain.interactors


import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.model.BookModel

interface GetBookListNetworkUseCase {
    suspend operator fun invoke(): ActionResult<List<BookModel>>
}