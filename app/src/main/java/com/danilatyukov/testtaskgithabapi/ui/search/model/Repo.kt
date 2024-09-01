package com.danilatyukov.testtaskgithabapi.ui.search.model

data class Repo(
    val name: String,
    val forks_count: Int,
    val description: String?,
    val contents_url: String
)

