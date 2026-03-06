package com.movieflick.ui.navigationbar

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.movieflick.navigation.Page
import com.movieflick.ui.feed.FeedPage
import com.movieflick.ui.feed.FeedViewModel
import com.movieflick.ui.main.MainRouter
import com.movieflick.utils.preview.composableHorizontalSlide
import com.movieflick.utils.sharedViewModel
import kotlin.reflect.KClass


@Composable
fun NavigationBarNestedGraph(
    navController: NavHostController,
    mainNavController: NavHostController,
    parentRoute: KClass<*>?
){
    NavHost(
        navController = navController,
        startDestination = Page.Feed,
        route = parentRoute
    ){

        composableHorizontalSlide<Page.Feed> { backStack ->
            val viewModel = hiltViewModel<FeedViewModel>()
            FeedPage(
                mainRouter = MainRouter(mainNavController),
                viewModel = viewModel,
                sharedViewModel = backStack.sharedViewModel(navController = mainNavController)
            )
        }

    }

}