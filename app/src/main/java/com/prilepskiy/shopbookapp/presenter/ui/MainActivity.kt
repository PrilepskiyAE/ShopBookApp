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


                    topBar = { TopAppBar(title = {  outlinedTextFieldComposable() }, backgroundColor = Color(0xff0f9d58)) },


                    content = {it

                        Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

                            val items = (1..100).map { "Item $it" }
                            val lazyListState = rememberLazyListState()
                            var scrolledY = 0f
                            var previousOffset = 0
                            LazyColumn(
                                Modifier.fillMaxSize(),
                                lazyListState,
                            ) {

                                item {

                                    LazyRow(
                                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {


                                        itemsIndexed(items = (1..10).toList()) { pos, _ ->

                                                Image(
                                        painter = painterResource(id = R.drawable.sample_image),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillWidth,
                                        modifier = Modifier
                                            .graphicsLayer {
                                                scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                                translationY = scrolledY * 0.5f
                                                previousOffset = lazyListState.firstVisibleItemScrollOffset
                                            }
                                            .height(240.dp)
                                            .fillMaxWidth()
                                    )

                                            }


                                    }
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
    fun outlinedTextFieldComposable():String {
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = { text = it },
            label = { Text("search books") }

        )
        return text
    }
}


