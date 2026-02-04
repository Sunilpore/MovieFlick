package com.movieflick.ui.navigationbar

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.movieflick.ui.main.MainRouter


@Composable
fun NavigationBarScreen(
    sharedViewModel: NavigationBarSharedViewModel,
    mainRouter: MainRouter,
    darkMode: Boolean,
    onThemeUpdated: () -> Unit,
    navHostController: NavHostController
){


}