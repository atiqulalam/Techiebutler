package com.techiebutler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.techiebutler.PostsPagingSource.Companion.PAGE_SIZE

class PostViewModel(
    private val repository: PostRemoteRepository
) : ViewModel() {

    val users = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PostsPagingSource(
                repository = repository
            )
        }
    )
        .flow
        .cachedIn(scope = viewModelScope)
}