package com.danilatyukov.testtaskgithabapi.ui.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Screen(val route: String) : Parcelable {
    object SearchScreen : Screen("searchScreen")
    object FileProviderScreen : Screen("fileProviderScreen")
    object WebViewScreen : Screen("webviewScreen")
    object ExternalBrowser : Screen("externalBrowser")
}