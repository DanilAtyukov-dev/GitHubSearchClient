package com.danilatyukov.testtaskgithabapi.ui.search

import android.view.KeyEvent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.danilatyukov.testtaskgithabapi.R

@Composable
fun SearchField(viewModel: SearchViewModel) {

    Row {
        TextField(
            modifier = Modifier.onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (viewModel.query.length>=3) viewModel.search()
                }
                false
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
            keyboardActions = KeyboardActions(
                onGo = { if (viewModel.query.length>=3) viewModel.search() },
            ),
            value = viewModel.query,
            placeholder = { Text(text = stringResource(id = R.string.Search)) },
            onValueChange = {
                viewModel.query = it
            })
        IconButton(onClick = { viewModel.search() }, enabled = viewModel.query.length>=3) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "search button", )
        }
    }
}