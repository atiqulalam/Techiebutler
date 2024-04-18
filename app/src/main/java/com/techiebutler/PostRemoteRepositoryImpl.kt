package com.techiebutler

class PostRemoteRepositoryImpl(
    private val postApi: PostApi
) : PostRemoteRepository {

    override suspend fun getPosts(since: Int, perPage: Int): List<PostDto> =
        postApi.getPost(
            since = since,
            perPage = perPage
        )
}