package com.movieflick.ui.navigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DynamicFeed
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import com.movieflick.navigation.Page



class NavigationBarUiState(
    val bottomItems: List<BottomNavigationBarItem> = listOf(
        BottomNavigationBarItem.Feed,
        BottomNavigationBarItem.MyFavorites
    )
)


sealed class BottomNavigationBarItem(
    val tabName: String,
    val imageVector: ImageVector,
    val page: Page,
){
    data object Feed : BottomNavigationBarItem("Feed", imageVector = Icons.Default.DynamicFeed, page = Page.Feed)
    data object MyFavorites : BottomNavigationBarItem("My Favourites", imageVector = Icons.Default.FavoriteBorder, page = Page.Favorites)
}




