package com.danilatyukov.testtaskgithabapi.ui.fileProvider


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilatyukov.testtaskgithabapi.domain.FileProviderRepository
import com.danilatyukov.testtaskgithabapi.domain.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FileProviderViewModel(
    val contentsUrl: String, var fileProviderRepository: FileProviderRepository
) : ViewModel() {
    private val _currentDirectoryContents = mutableStateOf(emptyList<ContentEntry>())
    private var _showLoader by mutableStateOf(false)
    private var _showError by mutableStateOf(false)
    private var requestJob: Job? = null

    init {
        navigateToDirectory(contentsUrl)
    }

    val showLoader get() = _showLoader
    val showError get() = _showError

    val currentDirectoryContents get() = _currentDirectoryContents

    fun navigateToDirectory(contentsUrl: String) {
        _showError = false
        _showLoader = true
        requestJob?.cancel()
        requestJob = viewModelScope.launch(Dispatchers.IO) {
            var resource =
                viewModelScope.async { fileProviderRepository.getDirStructure(contentsUrl) }.await()
            if (resource.status == Resource.Status.SUCCESS) {
                _currentDirectoryContents.value = resource.data?.entries!!.map {
                    ContentEntry(
                        if (it.type == "file") ContentEntry.Type.File else ContentEntry.Type.Dir,
                        it.name,
                        it.url,
                        it.html_url
                    )
                }.sortedByDescending { it.type }
            } else _showError = true
            _showLoader = false
        }
    }
}