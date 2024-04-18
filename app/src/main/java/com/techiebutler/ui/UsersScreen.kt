package com.techiebutler.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.techiebutler.DetailsActivity
import com.techiebutler.PostDto
import com.techiebutler.PostViewModel
import com.techiebutler.PostViewModelFactory

@Composable
fun UsersRoute(
    modifier: Modifier = Modifier,
    context: Context? = null
) {
    val viewModel: PostViewModel = viewModel(factory = PostViewModelFactory())
    val users = viewModel.users.collectAsLazyPagingItems()

    UsersScreen(
        modifier = modifier,
        users = users,context
    )
}

@Composable
fun UsersScreen(
    modifier: Modifier = Modifier,
    users: LazyPagingItems<PostDto>,
    context: Context? = null
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = if (users.itemCount < 1)
            Arrangement.Center
        else
            Arrangement.Top
    ) {
        when (users.loadState.refresh) {
            LoadState.Loading -> {
                item {
                    PaginationLoadingItem(circularProgressSize = 64.dp)
                }
            }
            is LoadState.Error -> {
                item {
                    PaginationErrorItem {
                        users.refresh()
                    }
                }
            }
            is LoadState.NotLoading -> {
                if (users.itemCount < 1) {
                    item {
                        EmptyItem()
                    }
                }
            }
        }
        items(items = users) { user ->
            UserItem(user = user,context = context)
        }
        when (users.loadState.append) {
            LoadState.Loading -> {
                item {
                    PaginationLoadingItem()
                }
            }
            is LoadState.Error -> {
                item {
                    PaginationRetryItem {
                        users.retry()
                    }
                }
            }
            is LoadState.NotLoading -> Unit
        }
    }

    @Composable
    fun onItemClick(item: String) {
        val intent = Intent(LocalContext.current, DetailsActivity::class.java).apply {
            putExtra("item_key", item)
        }
        LocalContext.current.startActivity(intent)
    }
}

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: PostDto?,
    context: Context? = null
) {

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("item", user)
                }
                context?.startActivity(intent)
            },
        //elevation = 8.dp,
        shape = RoundedCornerShape(8.dp),
       // colors = CardDefaults
    ){
        Column(modifier = Modifier.wrapContentHeight().fillMaxWidth().padding(8.dp)) {
            Text(
                modifier = modifier.padding(vertical = 6.dp),
                text = user?.title ?: "", style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = modifier.padding(vertical = 6.dp),
                text = user?.body ?: ""
            )
            Text(
                modifier = modifier.padding(vertical = 6.dp),
                text = user?.customData ?: ""
            )
        }
    }

}
