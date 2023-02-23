package com.prilepskiy.shopbookapp.presenter.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.prilepskiy.shopbookapp.R
import com.prilepskiy.shopbookapp.data.apiservice.BannerApiService
import com.prilepskiy.shopbookapp.presenter.theme.ShopBookAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getBooks()
        lifecycleScope.launch {
            viewModel.bookList.collectLatest {
                if (it != null) {
                    Log.d("TAG", "onCreate: ${it.size}")
                }
            }
        }
        setContent {
            ShopBookAppTheme {
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
        }
    }

    @Composable
    fun OutlinedTextFieldComposable():String {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(

            value = text,
            onValueChange = { text = it },
            label = { Text("search books") }

        )
        return text
    }

    @Composable
    fun Banners(list:List<Int>){
        val lazyListState = rememberLazyListState()
Box( ) {
    LazyRow(
        state = lazyListState

    ) {


        itemsIndexed(items = list) { pos, _ ->

            Image(
                painter = painterResource(list[pos]),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.width(450.dp).height(200.dp)
            )

        }


    }
    Box(Modifier.fillMaxHeight().padding(top = 190.dp).padding(horizontal = 150.dp)) {
        PageIndicator(

            numberOfPages =  list.size,
            selectedPage = lazyListState.firstVisibleItemIndex,

            )
    }

}

        LaunchedEffect(true) {
            repeat(Int.MAX_VALUE) {
                delay(5500)

                lazyListState.animateScrollToItem( it % list.size)
            }
        }







        }


    @Composable
    fun TopAppBarBookList(searchBook: (String)->Unit,cleanBook: ()->Unit){
        var searchValues by remember { mutableStateOf("") }
        TopAppBar(


            title= {searchValues= OutlinedTextFieldComposable() },
            backgroundColor =  Color(0xFFF39F0D),
            actions = {
                IconButton(onClick = { searchBook(searchValues)  }) {
                    Icon(Icons.Filled.Search, null)
                }
                IconButton(onClick = { cleanBook()  }) {
                    Icon(
                        Icons.Filled.Check, null,
                        Modifier
                            .height(30.dp)
                            .width(30.dp))
                }
            })


    }



    @Composable
    fun PageIndicator(
        numberOfPages: Int,
        modifier: Modifier = Modifier,
        selectedPage: Int = 0,
        selectedColor: Color = Color.Blue,
        defaultColor: Color = Color.LightGray,
        defaultRadius: Dp = 5.dp,
        selectedLength: Dp = 10.dp,
        space: Dp = 5.5.dp,
        animationDurationInMillis: Int = 300,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(space),
            modifier = modifier,
        ) {
            for (i in 0 until numberOfPages) {
                val isSelected = i == selectedPage
                PageIndicatorView(
                    isSelected = isSelected,
                    selectedColor = selectedColor,
                    defaultColor = defaultColor,
                    defaultRadius = defaultRadius,
                    selectedLength = selectedLength,
                    animationDurationInMillis = animationDurationInMillis,
                )
            }
        }
    }
    @Composable
    fun PageIndicatorView(
        isSelected: Boolean,
        selectedColor: Color,
        defaultColor: Color,
        defaultRadius: Dp,
        selectedLength: Dp,
        animationDurationInMillis: Int,
        modifier: Modifier = Modifier,
    ) {

        val color: Color by animateColorAsState(
            targetValue = if (isSelected) {
                selectedColor
            } else {
                defaultColor
            },
            animationSpec = tween(
                durationMillis = animationDurationInMillis,
            )
        )
        val width: Dp by animateDpAsState(
            targetValue = if (isSelected) {
                selectedLength
            } else {
                defaultRadius
            },
            animationSpec = tween(
                durationMillis = animationDurationInMillis,
            )
        )

        Canvas(
            modifier = modifier
                .size(
                    width = width,
                    height = defaultRadius,
                ),
        ) {
            drawRoundRect(
                color = color,
                topLeft = Offset.Zero,
                size = Size(
                    width = width.toPx(),
                    height = defaultRadius.toPx(),
                ),
                cornerRadius = CornerRadius(
                    x = defaultRadius.toPx(),
                    y = defaultRadius.toPx(),
                ),
            )
        }
    }

}



