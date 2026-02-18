package com.movieflick.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefresh(
    modifier: Modifier = Modifier,
    refresh: Boolean = false,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
){

    val state = rememberPullToRefreshState()

    PullToRefreshBox(
        state = state,
        isRefreshing = refresh,
        onRefresh = onRefresh,
        modifier = modifier
    ){
        content()
    }

}
