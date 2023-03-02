package com.prilepskiy.shopbookapp.presenter.ui.сomposable

import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.prilepskiy.shopbookapp.data.apiservice.BannerApiService
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.presenter.ui.MainViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreenComposable(bannerList:List<Int>, bookList: Flow<PagingData<BookModel>>){
    Scaffold(
        topBar = { TopAppBarBookList({},{}) },
        content = {it
            HomeScreenContentComposable(bannerList,bookList)

        }
    )
}
@Composable
fun HomeScreenContentComposable(bannerList:List<Int>, bookList: Flow<PagingData<BookModel>>){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Log.d("TAG99", "HomeScreenContentComposable:$bookList ")
        //val items = bookList
        val lazyListState = rememberLazyListState()
        val lazyBookItems: LazyPagingItems<BookModel> = bookList.collectAsLazyPagingItems()

        LazyColumn(
            Modifier.fillMaxSize(),
            lazyListState,
        ) {

            item {

                Banners(bannerList)
            }

            val loadState =lazyBookItems.loadState.mediator
            items(lazyBookItems) {

                Text(
                    text =it?.title?:"NULL" ,
                    Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}
