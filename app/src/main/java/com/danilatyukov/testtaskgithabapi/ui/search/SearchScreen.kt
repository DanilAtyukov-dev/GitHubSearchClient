package com.danilatyukov.testtaskgithabapi.ui.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.danilatyukov.testtaskgithabapi.R
import com.danilatyukov.testtaskgithabapi.ui.common.ErrorPlaceholder
import com.danilatyukov.testtaskgithabapi.ui.search.model.Repo
import com.danilatyukov.testtaskgithabapi.ui.search.model.User
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    navToFProviderScreen: (url: String) -> Unit,
    navToExternalBrowser: (url: String) -> Unit
) {
    val viewModel = koinViewModel<SearchViewModel>()

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        SearchField(viewModel = viewModel)

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
                            UserCard(
                                modifier = Modifier.padding(10.dp),
                                user = it,
                                onClick = { navToExternalBrowser(it.url) })
                        }
                    }
                }
            }
            if (viewModel.showLoader) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            if (viewModel.showError) ErrorPlaceholder(
                modifier = Modifier.align(Alignment.Center),
                stringResource(id = R.string.ErrorMsg),
                stringResource(id = R.string.ErrorBtn)
            ) { viewModel.search() }
        }
    }
}