package com.danilatyukov.testtaskgithabapi.data.network
import com.danilatyukov.testtaskgithabapi.BuildConfig
import com.danilatyukov.testtaskgithabapi.data.network.model.RepoStructureResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.ReposSearchResponseBody
import com.danilatyukov.testtaskgithabapi.data.network.model.UsersSearchResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

interface GitHubApi {
    @GET("/search/users")
    @Headers("Accept: application/vnd.github+json",
        "Authorization: Bearer ${BuildConfig.GH_PAT}",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun searchUser(@Query("q") q: String, @Query("page") pageNum: Int, @Query("per_page") perPage: Int): Response<UsersSearchResponseBody>

    @GET("/search/repositories")
    @Headers("Accept: application/vnd.github+json",
        "Authorization: Bearer ${BuildConfig.GH_PAT}",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun searchRepo(@Query("q") q: String, @Query("page") pageNum: Int, @Query("per_page") perPage: Int): Response<ReposSearchResponseBody>

    @GET
    @Headers("Accept: application/vnd.github.v3.object",
        "Authorization: Bearer ${BuildConfig.GH_PAT}",
        "X-GitHub-Api-Version: 2022-11-28")
    suspend fun getDirStructure(@Url url: String): Response<RepoStructureResponseBody>
}