package com.danilatyukov.testtaskgithabapi.ui.fileProvider

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.danilatyukov.testtaskgithabapi.R
import com.danilatyukov.testtaskgithabapi.ui.theme.Orange
import com.danilatyukov.testtaskgithabapi.ui.theme.shape3

@Composable
fun ContentEntryCard(
    modifier: Modifier = Modifier,
    contentsEntry: ContentEntry,
    onClick: () -> Unit
) {
    ElevatedCard(modifier = modifier, onClick = onClick, shape = shape3)
    {
        Row {
            when (contentsEntry.type) {
                ContentEntry.Type.File -> {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.ic_file),
                        contentDescription = "file ${contentsEntry.name}",
                        colorFilter = ColorFilter.tint(Color.Gray)
                    )
                }

                ContentEntry.Type.Dir -> {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = painterResource(id = R.drawable.ic_folder),
                        contentDescription = "folder ${contentsEntry.name}",
                        colorFilter = ColorFilter.tint(Orange),
                    )
                }
            }

            Text(modifier = Modifier.padding(start = 10.dp), text = contentsEntry.name, color = Color.Gray)
        }
    }
}