package com.prilepskiy.shopbookapp.presenter.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.prilepskiy.shopbookapp.R
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

                                    Banners(items)
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
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = { Text("search books") }

        )
        return text
    }

    @Composable
    fun Banners(list:List<String>){
        val lazyListState = rememberLazyListState()
        LazyRow(
            state = lazyListState
        ) {


            itemsIndexed(items = list) { pos, _ ->

                Image(
                    painter = painterResource(id = R.drawable.sample_image),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.width(450.dp)
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
        TopAppBar(modifier = Modifier.height(100.dp),

            elevation = 4.dp,
            title= {searchValues= OutlinedTextFieldComposable() },
            backgroundColor =  Color(0xFFF39F0D),
            actions = {
                IconButton(onClick = { searchBook(searchValues)  }) {
                    Icon(Icons.Filled.Search, null,
                        Modifier
                            .height(30.dp)
                            .width(30.dp))
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

}



