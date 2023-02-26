package com.prilepskiy.shopbookapp.domain.interactors

import com.prilepskiy.shopbookapp.core.ActionResult
import com.prilepskiy.shopbookapp.domain.model.BookModel

interface GetListBannerUseCase {
    suspend operator fun invoke(): List<Int>
}