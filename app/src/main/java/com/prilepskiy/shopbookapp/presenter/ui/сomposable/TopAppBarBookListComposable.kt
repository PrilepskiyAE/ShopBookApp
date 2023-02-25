package com.prilepskiy.shopbookapp.presenter.ui.сomposable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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
