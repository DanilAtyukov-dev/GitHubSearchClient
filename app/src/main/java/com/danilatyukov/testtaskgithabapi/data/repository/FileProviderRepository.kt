package com.danilatyukov.testtaskgithabapi.data.repository

import com.danilatyukov.testtaskgithabapi.data.network.model.RepoStructureResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.ReposSearchResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.Resource

interface FileProviderRepository {
    suspend fun searchRepo(q: String, pageNum: Int, perPage: Int): Resource<ReposSearchResponseBody>
    suspend fun getDirStructure(url: String): Resource<RepoStructureResponseBody>
}