package com.danilatyukov.testtaskgithabapi.domain

import com.danilatyukov.testtaskgithabapi.data.network.model.ReposSearchResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.UsersSearchResponseBody

interface SearchRepository {
    suspend fun searchRepo(q: String, pageNum: Int, perPage: Int): Resource<ReposSearchResponseBody>
    suspend fun searchUser(q: String, pageNum: Int, perPage: Int): Resource<UsersSearchResponseBody>
}