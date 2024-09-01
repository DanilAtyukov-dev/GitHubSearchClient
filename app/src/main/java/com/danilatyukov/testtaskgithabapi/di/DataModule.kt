package com.danilatyukov.testtaskgithabapi.di

import com.danilatyukov.testtaskgithabapi.data.network.GitHubApi
import com.danilatyukov.testtaskgithabapi.domain.FileProviderRepository
import com.danilatyukov.testtaskgithabapi.data.repository.GitHubApiRepositoryImpl
import com.danilatyukov.testtaskgithabapi.domain.SearchRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val baseUrl = "https://api.github.com/"

val dataModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GitHubApi::class.java)
    }

    single<SearchRepository> {
        GitHubApiRepositoryImpl(get())
    }

    single<FileProviderRepository> {
        GitHubApiRepositoryImpl(get())
    }
}
