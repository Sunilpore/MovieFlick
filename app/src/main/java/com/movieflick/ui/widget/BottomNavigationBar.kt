package com.movieflick.ui.widget

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.movieflick.navigation.route
import com.movieflick.ui.navigationbar.BottomNavigationBarItem
import com.movieflick.utils.preview.PreviewContainer


@Composable
fun BottomNavigationBar(
    items:List<BottomNavigationBarItem>,
    navController: NavController,
    onItemClick: (BottomNavigationBarItem) -> Unit
){

    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar {

        items.forEach { item ->
            val selected = item.page.route() == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(imageVector = item.imageVector, contentDescription = null)
                },
                label = {
                    Text(text = item.tabName)
                }
            )
        }
    }
}


@Preview("Light")
@Preview("Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BottomNavigationBarPreview(){
    PreviewContainer {
        BottomNavigationBar(listOf(BottomNavigationBarItem.Feed, BottomNavigationBarItem.MyFavorites),
            rememberNavController()){}
    }
}





