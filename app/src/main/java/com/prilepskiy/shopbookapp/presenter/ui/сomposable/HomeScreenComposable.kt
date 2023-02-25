package com.prilepskiy.shopbookapp.presenter.ui.сomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.prilepskiy.shopbookapp.data.apiservice.BannerApiService

@Composable
fun HomeScreenComposable(){
    Scaffold(
        topBar = { TopAppBarBookList({},{}) },
        content = {it

            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                val items = (1..100).map { "Item $it" }
                val lazyListState = rememberLazyListState()

                LazyColumn(
                    Modifier.fillMaxSize(),
                    lazyListState,
                ) {

                    item {

                        Banners(BannerApiService.getListBanner())
                    }

                    items(items) {
                        Text(
                            text = it,
                            Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                }
            }
        }
    )
}