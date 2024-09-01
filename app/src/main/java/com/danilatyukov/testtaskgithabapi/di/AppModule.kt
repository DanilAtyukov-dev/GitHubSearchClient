package com.danilatyukov.testtaskgithabapi.di

import com.danilatyukov.testtaskgithabapi.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        SearchViewModel(get())
    }
}
