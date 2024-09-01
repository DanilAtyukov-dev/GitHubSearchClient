package com.danilatyukov.testtaskgithabapi.ui.search

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danilatyukov.testtaskgithabapi.R
import com.danilatyukov.testtaskgithabapi.ui.common.ErrorPlaceholder
import com.danilatyukov.testtaskgithabapi.ui.search.model.Repo
import com.danilatyukov.testtaskgithabapi.ui.search.model.User
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(modifier: Modifier = Modifier, navToFProviderScreen: (url: String) -> Unit) {
    val viewModel = koinViewModel<SearchViewModel>()
    val context = LocalContext.current
    var query by rememberSaveable {
        mutableStateOf("")
    }
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            TextField(
                value = query,
                placeholder = { Text(text = stringResource(id = R.string.Search)) },
                onValueChange = {
                    query = it
                })
            IconButton(onClick = { viewModel.search(query) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "search button")
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(
                    items = viewModel.cards.value,
                ) {
                    when (it) {
                        is Repo -> {
                            RepoCard(
                                modifier = Modifier.padding(10.dp),
                                repo = it,
                                onClick = { navToFProviderScreen(it.contents_url) })
                        }

                        is User -> {
                            val openURL = Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
                            UserCard(
                                modifier = Modifier.padding(10.dp),
                                user = it,
                                onClick = { context.startActivity(openURL) })
                        }
                    }
                }
            }
            if (viewModel.showLoader) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            if (viewModel.showError) ErrorPlaceholder(
                modifier = Modifier.align(Alignment.Center),
                stringResource(id = R.string.ErrorMsg),
                stringResource(id = R.string.ErrorBtn)
            ) { viewModel.search(query) }
        }
    }
}