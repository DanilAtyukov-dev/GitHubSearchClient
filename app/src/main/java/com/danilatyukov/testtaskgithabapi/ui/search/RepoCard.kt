package com.danilatyukov.testtaskgithabapi.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.danilatyukov.testtaskgithabapi.R
import com.danilatyukov.testtaskgithabapi.ui.search.model.Repo
import com.danilatyukov.testtaskgithabapi.ui.theme.shape3

@Composable
fun RepoCard(modifier: Modifier = Modifier, repo: Repo, onClick: () -> Unit) {
    ElevatedCard(modifier = modifier, onClick = onClick, shape = shape3) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = repo.name)
                Text(
                    text = "${repo.forks_count}\n${stringResource(id = R.string.Forks)}",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,

                )
            }
            repo.description?.let { Text(text = it, color = Color.Gray,) }
        }
    }
}

