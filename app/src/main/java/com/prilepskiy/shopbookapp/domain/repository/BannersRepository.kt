package com.prilepskiy.shopbookapp.domain.repository

interface BannersRepository {
  suspend fun getListBanner():List<Int>
}