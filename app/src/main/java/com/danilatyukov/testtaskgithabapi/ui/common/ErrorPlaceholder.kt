package com.danilatyukov.testtaskgithabapi.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorPlaceholder(modifier: Modifier, msg: String, btnText: String, onClick:() -> Unit){
    Column(modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = msg)
        Button(onClick = onClick) {
            Text(text = btnText)
        }
    }
}