package com.danilatyukov.testtaskgithabapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danilatyukov.testtaskgithabapi.ui.navigation.Navigation
import com.danilatyukov.testtaskgithabapi.ui.theme.TestTaskGithabAPITheme
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.core.scope.Scope

class MainActivity : ComponentActivity(), AndroidScopeComponent {
    override val scope: Scope by activityScope()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TestTaskGithabAPITheme {
                Scaffold(modifier = Modifier.safeDrawingPadding()) { paddings ->
                    Content(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(paddings)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Content(modifier: Modifier) {
    Navigation(modifier)
}