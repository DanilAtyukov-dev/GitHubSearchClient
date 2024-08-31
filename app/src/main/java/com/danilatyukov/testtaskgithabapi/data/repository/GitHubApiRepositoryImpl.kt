package com.danilatyukov.testtaskgithabapi.data.repository

import com.danilatyukov.testtaskgithabapi.data.network.GitHubApi
import com.danilatyukov.testtaskgithabapi.data.network.model.RepoStructureResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.ReposSearchResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.Resource
import com.danilatyukov.testtaskgithabapi.data.network.model.UsersSearchResponseBody


class GitHubApiRepositoryImpl(private val api: GitHubApi) : SearchRepository, FileProviderRepository {
    override suspend fun searchUser(q: String, pageNum: Int, perPage: Int): Resource<UsersSearchResponseBody> {
        return try {
            val response = api.searchUser(q, pageNum, perPage)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(response.errorBody()?.string() ?: response.message(), null)

            } else {
                Resource.error(response.errorBody()?.string() ?: response.message(), null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error("ERROR", null)
        }
    }

    override suspend fun searchRepo(q: String, pageNum: Int, perPage: Int): Resource<ReposSearchResponseBody> {
        return try {
            val response = api.searchRepo(q, pageNum, perPage)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(response.errorBody()?.string() ?: response.message(), null)
            } else {
                Resource.error(response.errorBody()?.string() ?: response.message(), null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error("ERROR", null)
        }
    }

    override suspend fun getDirStructure(url: String): Resource<RepoStructureResponseBody> {
        return try {
            val response = api.getDirStructure(url)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(response.errorBody()?.string() ?: response.message(), null)
            } else {
                Resource.error(response.errorBody()?.string() ?: response.message(), null)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.error("ERROR", null)
        }
    }
}