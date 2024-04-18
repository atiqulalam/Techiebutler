package com.techiebutler

import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("posts")
    suspend fun getPost(
        @Query("_start") since: Int,
        @Query("_limit") perPage: Int
    ): List<PostDto>
}