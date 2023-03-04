package com.prilepskiy.shopbookapp.presenter.ui.сomposable

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.prilepskiy.shopbookapp.data.apiservice.BannerApiService
import com.prilepskiy.shopbookapp.domain.model.BookModel
import com.prilepskiy.shopbookapp.presenter.ui.MainViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
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

                if (it != null) {
                    BooksListItem(it,{})
                }
            }
        }
    }
}


@Composable
fun BooksListItem(book: BookModel, selectedItem: (BookModel) -> Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 10.dp,
        shape = RoundedCornerShape(corner = CornerSize(10.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
                .clickable { selectedItem(book) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BookImage(book = book)

            Text(text = book.title.toString(), style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
          

        }

    }

}
@Composable
fun BookImage(book: BookModel) {

    GlideImage(
        imageModel = {book.formats.image_jpeg} ,
        imageOptions = ImageOptions(
            contentDescription = null,
            contentScale = ContentScale.Crop,
        ), modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth())
}
