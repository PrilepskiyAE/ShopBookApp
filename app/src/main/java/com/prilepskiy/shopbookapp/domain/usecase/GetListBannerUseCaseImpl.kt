package com.prilepskiy.shopbookapp.domain.usecase

import com.prilepskiy.shopbookapp.domain.interactors.GetListBannerUseCase
import com.prilepskiy.shopbookapp.domain.repository.BannersRepository

class GetListBannerUseCaseImpl(private val bannersRepository: BannersRepository): GetListBannerUseCase {
    override suspend fun invoke(): List<Int> {
        return bannersRepository.getListBanner()
    }
}