package com.movieflick.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.movieflick.navigation.Graph
import com.movieflick.navigation.Page
import com.movieflick.ui.navigationbar.NavigationBarNestedGraph
import com.movieflick.ui.navigationbar.NavigationBarScreen
import com.movieflick.utils.preview.composableHorizontalSlide
import com.movieflick.utils.sharedViewModel


@Composable
fun MainGraph(
    mainNavController: NavHostController,
    darkMode: Boolean,
    onThemeUpdated: () -> Unit
) {

    NavHost(
        navController = mainNavController,
        startDestination = Page.NavigationBar,
        route = Graph.Main::class
    ){
        composableHorizontalSlide<Page.NavigationBar> { backStack ->
            val nestedValController = rememberNavController()

            NavigationBarScreen(
                sharedViewModel = backStack.sharedViewModel(navController = mainNavController),
                mainRouter = MainRouter(mainNavController),
                darkMode = darkMode,
                onThemeUpdated = onThemeUpdated,
                nestedNavController = nestedValController
            ){
                NavigationBarNestedGraph(
                    navController = nestedValController,
                    mainNavController = mainNavController,
                    parentRoute = Graph.Main::class
                )
            }
        }


        composableHorizontalSlide<Page.MovieDetails> {

        }


    }


}
