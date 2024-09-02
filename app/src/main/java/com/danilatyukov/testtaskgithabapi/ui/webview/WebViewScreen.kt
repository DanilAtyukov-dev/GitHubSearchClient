package com.danilatyukov.testtaskgithabapi.ui.webview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danilatyukov.testtaskgithabapi.R
import com.danilatyukov.testtaskgithabapi.ui.common.ErrorPlaceholder

@Composable
fun WebViewScreen(modifier: Modifier = Modifier, url: String) {
    var showError by rememberSaveable { mutableStateOf(false) }
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (!showError) {
            WebView(url = url, changeErrorState = {
                showError = it
            })
        }
        if (showError) {
            ErrorPlaceholder(
                msg = stringResource(id = R.string.ErrorMsg),
                btnText = stringResource(id = R.string.ErrorBtn)
            ) {
                showError = false
            }
        }

    }
}

