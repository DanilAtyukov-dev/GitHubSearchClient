package com.danilatyukov.testtaskgithabapi.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.danilatyukov.testtaskgithabapi.ui.search.model.User
import com.danilatyukov.testtaskgithabapi.ui.theme.Orange
import com.danilatyukov.testtaskgithabapi.ui.theme.shape3

@Composable
fun UserCard(modifier: Modifier = Modifier, user: User, onClick: () -> Unit) {
    ElevatedCard(modifier = modifier, onClick = onClick, shape = shape3) {
        Row(
            modifier = Modifier.padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = Modifier.size(50.dp),
                model = user.avatar_url,
                contentDescription = "avatar"
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 15.dp), text = user.login
            )
            Text(
                text = user.score.toString(),
                color = Orange,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}