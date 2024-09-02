package com.danilatyukov.testtaskgithabapi.ui.navigation

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.danilatyukov.testtaskgithabapi.ui.fileProvider.FileProviderScreen
import com.danilatyukov.testtaskgithabapi.ui.search.SearchScreen
import com.danilatyukov.testtaskgithabapi.ui.webview.WebViewScreen
import java.net.URLEncoder


@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val startDestination = Screen.SearchScreen.route
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                navToFProviderScreen = {
                    val encodedUrl = URLEncoder.encode(it)
                    navController.navigate(Screen.FileProviderScreen.route + "/${encodedUrl}")
                },
                navToExternalBrowser = {

                    val browser = Intent.makeMainSelectorActivity(
                        Intent.ACTION_MAIN,
                        Intent.CATEGORY_APP_BROWSER,
                    )
                    browser.setData(Uri.parse(it))
                    context.startActivity(browser)

                })
        }
        composable(
            Screen.FileProviderScreen.route + "/{contentsUrl}",
            arguments = listOf(navArgument("contentsUrl") { type = NavType.StringType })
        ) {
            FileProviderScreen(contentsUrl = it.arguments?.getString("contentsUrl") ?: "",
                navigateToDirectory = {
                    val encodedUrl = URLEncoder.encode(it)
                    navController.navigate(Screen.FileProviderScreen.route + "/${encodedUrl}")
                },
                openFile = {
                    val encodedUrl = URLEncoder.encode(it)
                    navController.navigate(route = Screen.WebViewScreen.route + "/${encodedUrl}")
                })
        }
        composable(
            Screen.WebViewScreen.route + "/{htmlUrl}",
            arguments = listOf(navArgument("htmlUrl") { type = NavType.StringType })
        ) {
            WebViewScreen(url = it.arguments?.getString("htmlUrl") ?: "")
        }
    }
}
