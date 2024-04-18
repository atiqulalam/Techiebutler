package com.techiebutler.ui

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.techiebutler.PostDto
import com.techiebutler.ui.theme.TechiebutlerTheme
import kotlinx.coroutines.flow.flowOf

@Composable
@PreviewAllScreens
fun PreviewUsersScreen() {
    TechiebutlerTheme {

        val postsDtos = listOf(
            PostDto(title = "nickname1"),
            PostDto(title = "nickname2"),
            PostDto(title = "nickname3")
        )

        val users = flowOf(value = PagingData.from(data = postsDtos))
            .collectAsLazyPagingItems()

        UsersScreen(users = users)
    }
}

@Composable
@PreviewAllScreens
fun PreviewEmptyUsersScreen() {
    TechiebutlerTheme {

        val users = flowOf(value = PagingData.empty<PostDto>())
            .collectAsLazyPagingItems()

        UsersScreen(users = users)
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationUserItem() {
    TechiebutlerTheme {
        UserItem(
            user = PostDto(
                title = "test_nickname"
            )
        )
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationLoadingItem() {
    TechiebutlerTheme {
        PaginationLoadingItem()
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationErrorItem() {
    TechiebutlerTheme {
        PaginationErrorItem {}
    }
}

@Composable
@PreviewWithBackground
fun PreviewEmptyItem() {
    TechiebutlerTheme {
        EmptyItem()
    }
}

@Composable
@PreviewWithBackground
fun PreviewPaginationRetryItem() {
    TechiebutlerTheme {
        PaginationRetryItem {}
    }
}