# GitHub Search Client

A mobile app for finding users and repositories on GitHub.

# Screenshots

| Search Screen                  | File Provider Screen                 | Video (youtube link)                                                                      |
|--------------------------------|--------------------------------------|-------------------------------------------------------------------------------------------|
| ![](pictures/SearchScreen.png) | ![](pictures/FileProviderScreen.png) | [![Watch the video](pictures/VideoPlay.png)](https://www.youtube.com/watch?v=FoqRNIhskU0) |

# Architecture

| Navigation Graph                  | Dependency Graph                  |
|-----------------------------------|-----------------------------------|
| ![](pictures/NavGraph.drawio.png) | ![](pictures/DepGraph.drawio.png) |

## Technology stack

Jetpack Compose, Jetpack Navigation, MVVM / MVI, Coroutines, Retrofit, Koin.

## Credentials

The personal GitHub access token should be stored in the user's /Users/{username}/.gradle folder
in the gradle.properties file in the parameter GH_PAT=YourPAT