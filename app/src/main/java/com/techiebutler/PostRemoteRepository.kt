package com.techiebutler

interface PostRemoteRepository {

    suspend fun getPosts(
        since: Int,
        perPage: Int
    ): List<PostDto>
}