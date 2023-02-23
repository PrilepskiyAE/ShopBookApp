package com.prilepskiy.shopbookapp.data.apiservice

import com.prilepskiy.shopbookapp.R
//TODO Imitation of getting a list from the Internet
object BannerApiService {
  private  val listBanner:List<Int> = listOf(
        R.drawable.sample_image,
        R.drawable.banner1,
        R.drawable.banner2,
        R.drawable.banner3,
        R.drawable.banner4,
        R.drawable.banner5,
        R.drawable.banner6,
        R.drawable.banner7,
        R.drawable.banner8,
        R.drawable.banner9,
        R.drawable.banner10
    )
    fun getListBanner():List<Int>{
        return listBanner
    }

}