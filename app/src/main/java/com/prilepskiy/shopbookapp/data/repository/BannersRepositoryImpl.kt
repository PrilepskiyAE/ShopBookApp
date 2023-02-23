package com.prilepskiy.shopbookapp.data.repository

import com.prilepskiy.shopbookapp.data.apiservice.BannerApiService
import com.prilepskiy.shopbookapp.domain.repository.BannersRepository
import javax.inject.Inject

class BannersRepositoryImpl @Inject constructor(): BannersRepository {
    override suspend fun getListBanner(): List<Int> {
        return BannerApiService.getListBanner()
    }
}