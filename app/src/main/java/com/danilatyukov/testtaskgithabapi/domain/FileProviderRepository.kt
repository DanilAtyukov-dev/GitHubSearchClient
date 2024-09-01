package com.danilatyukov.testtaskgithabapi.domain

import com.danilatyukov.testtaskgithabapi.data.network.model.RepoStructureResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.ReposSearchResponseBody

interface FileProviderRepository {
    suspend fun searchRepo(q: String, pageNum: Int, perPage: Int): Resource<ReposSearchResponseBody>
    suspend fun getDirStructure(url: String): Resource<RepoStructureResponseBody>
}