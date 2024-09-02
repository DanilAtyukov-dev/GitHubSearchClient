package com.danilatyukov.testtaskgithabapi.ui.fileProvider

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun FileProviderScreen(
    modifier: Modifier = Modifier,
    contentsUrl: String,
    navigateToDirectory:(contentsUrl: String) -> Unit,
    openFile:(htmlUrl: String) -> Unit
) {
    val viewModel: FileProviderViewModel = koinViewModel<FileProviderViewModel>(parameters = { parametersOf(contentsUrl) })

    Column(modifier) {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(
                    items = viewModel.currentDirectoryContents.value,
                ) {
                    ContentEntryCard(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), contentsEntry = it) {
                        when(it.type){
                            ContentEntry.Type.File ->  it.htmlUrl?.let(openFile)
                            ContentEntry.Type.Dir -> navigateToDirectory(it.contentsUrl)
                        }
                    }
                }
            }
            if (viewModel.showLoader) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            if (viewModel.showError) ErrorPlaceholder(
                modifier = Modifier.align(Alignment.Center),
                stringResource(id = R.string.ErrorMsg),
                stringResource(id = R.string.ErrorBtn)
            ) {
                viewModel.navigateToDirectory(viewModel.contentsUrl)
            }
        }
    }
}