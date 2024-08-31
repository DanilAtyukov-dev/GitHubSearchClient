package com.danilatyukov.testtaskgithabapi.data.network.model

data class RepoStructureResponseBody(
    val _links: Links,
    val download_url: String,
    val entries: List<Entry>,
    val git_url: String,
    val html_url: String,
    val name: String,
    val path: String,
    val sha: String,
    val size: Int,
    val type: String,
    val url: String
){
    data class Entry(
        val _links: Links,
        val download_url: String,
        val git_url: String,
        val html_url: String,
        val name: String,
        val path: String,
        val sha: String,
        val size: Int,
        val type: String,
        val url: String
    )

    data class Links(
        val git: String,
        val html: String,
        val self: String
    )
}