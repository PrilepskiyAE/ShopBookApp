package com.prilepskiy.shopbookapp.presenter.ui.сomposable

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.sp

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


        title= {Button(onClick = {},Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF0F9D58), contentColor = Color.Black)
        )
        {
            Text("Search", fontSize = 16.sp)
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "search",
                tint = Color(0xFFF39F0D)
            )
        } },
        backgroundColor =  Color(0xFFF39F0D)

       )


}
