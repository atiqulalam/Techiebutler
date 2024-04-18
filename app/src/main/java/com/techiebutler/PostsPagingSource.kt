package com.techiebutler

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlin.random.Random

class PostsPagingSource(
    private val repository: PostRemoteRepository
) : PagingSource<Int, PostDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostDto> =
        try {
            val perPage = params.key ?: PAGE_SIZE
            val since = perPage - PAGE_SIZE
            val posts = repository.getPosts(since = since, perPage = perPage)
            posts.map {
                val currentTime = System.currentTimeMillis()
                it.customData = generateRandomString(9)
                val timeTaken = (System.currentTimeMillis() - currentTime)
                Log.d("computation time taken ms -",timeTaken.toString())
            }
            LoadResult.Page(
                data = posts,
                prevKey = null,
                nextKey = if (posts.isEmpty()) null else perPage + PAGE_SIZE
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, PostDto>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(PAGE_SIZE) ?: anchorPage?.nextKey?.minus(PAGE_SIZE)
        }

    companion object {
        const val PAGE_SIZE = 20
    }


    private fun generateRandomString(length: Int): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9') // Define the characters allowed in the random string
        val stringBuilder = StringBuilder(length)
        val random = Random.Default

        repeat(length) {
            val randomIndex = random.nextInt(chars.size)
            stringBuilder.append(chars[randomIndex])
        }

        return stringBuilder.toString()
    }

}