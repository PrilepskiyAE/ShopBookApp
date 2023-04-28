package com.prilepskiy.shopbookapp.presenter.ui.сomposable

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
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
import androidx.compose.ui.unit.sp
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
import java.time.format.TextStyle

@Composable
fun HomeScreenComposable(bannerList: List<Int>, bookList: Flow<PagingData<BookModel>>) {
    Scaffold(
        topBar = { TopAppBarBookList({}, {}) },
        content = {
            it
            HomeScreenContentComposable(bannerList, bookList)

        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenContentComposable(bannerList: List<Int>, bookList: Flow<PagingData<BookModel>>) {
    Column() {
        Log.d("TAG99", "HomeScreenContentComposable:$bookList ")
        //val items = bookList
        val lazyListState = rememberLazyListState()
        val lazyBookItems: LazyPagingItems<BookModel> = bookList.collectAsLazyPagingItems()

        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green)
            .weight(weight = 1f)) {
            Banners(bannerList)
        }
        Box(modifier = Modifier
            .background(Color.Green)
            .fillMaxWidth()
            .weight(weight = 2f)) {
            val loadState = lazyBookItems.loadState.mediator

                    LazyColumn(
                        Modifier.fillMaxSize(),
                        lazyListState,
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(lazyBookItems){
                        if (it != null) {
                            BooksListItem(it, {})
                        }
                    }

                }
            }




//
//            item {
//
//
//            }

//            item {
//                LazyVerticalStaggeredGrid(
//                    state = rememberLazyStaggeredGridState(),
//                    columns = StaggeredGridCells.Fixed(2),
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalArrangement = Arrangement.spacedBy(10.dp),
//                    verticalArrangement = Arrangement.spacedBy(10.dp),
//                    content = {
//

//                    }
//                )
//            }

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

            Row( modifier = Modifier .fillMaxWidth()) {
                BookImage(book = book)
                Text(text = book.title.toString(), style = MaterialTheme.typography.body1,modifier = Modifier.padding(10.dp).wrapContentSize(Alignment.Center))
            }


            Spacer(modifier = Modifier.height(4.dp))




    }

}

@Composable
fun BookImage(book: BookModel) {

    GlideImage(
        imageModel = { book.formats.image_jpeg },
        imageOptions = ImageOptions(
            contentDescription = null,
            contentScale = ContentScale.Crop,
        ), modifier = Modifier
            .height(80.dp)
            .width(80.dp)
            .padding(2.dp)

    )
}
