package com.danilatyukov.testtaskgithabapi.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilatyukov.testtaskgithabapi.data.network.model.Resource
import com.danilatyukov.testtaskgithabapi.data.repository.SearchRepository
import com.danilatyukov.testtaskgithabapi.ui.search.model.Repo
import com.danilatyukov.testtaskgithabapi.ui.search.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchRepository: SearchRepository,
) : ViewModel() {
    private var _cards = mutableStateOf<List<Any>>(emptyList())
    private var _showLoader by mutableStateOf(false)
    private var _showError by mutableStateOf(false)
    private var requestJob: Job? = null

    val showLoader get() = _showLoader
    val showError get() = _showError
    val cards get() = _cards

    fun search(text: String, pageNum: Int = 1, perPage: Int = 20) {
        _showLoader = true
        _showError = false
        requestJob?.cancel()
        requestJob = viewModelScope.launch(Dispatchers.IO) {
            val users = viewModelScope.async { searchRepository.searchUser(text, pageNum, perPage) }
            val repos = viewModelScope.async { searchRepository.searchRepo(text, pageNum, perPage) }
            if (users.await().status == Resource.Status.SUCCESS || repos.await().status == Resource.Status.SUCCESS) {
                _cards.value = ((users.await().data?.items?.map {
                    User(
                        it.avatar_url, it.login, it.score, it.html_url
                    )
                } ?: emptyList()) + (repos.await().data?.items?.map {
                    Repo(
                        it.name,
                        it.forks_count,
                        it.description,
                        it.contents_url.replace("{+path}", "")
                    )
                } ?: emptyList())).sort()
            } else _showError = true
            _showLoader = false
        }
    }
}

private fun List<Any>.sort(): List<Any> = sortedBy {
    when (it) {
        is Repo -> it.name
        is User -> it.login
        else -> throw IllegalArgumentException("Impossible type")
    }
}