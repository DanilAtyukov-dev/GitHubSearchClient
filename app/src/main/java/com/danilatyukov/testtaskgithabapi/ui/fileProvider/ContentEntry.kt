package com.danilatyukov.testtaskgithabapi.ui.fileProvider

data class ContentEntry(
    var type: Type,
    var name: String,
    var contentsUrl: String,
    var htmlUrl: String?
){
    enum class Type{
        File,
        Dir
    }
}
