package com.valu.valulibrary.ui.nav

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.home.ScreenHome
import com.valu.valulibrary.ui.splash.ScreenSplash

@Composable
fun ValeNavigation(viewModel: AppViewModel, paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenVale.ScreenSplash.route,
        route = ROOT_GRAPH_ROUTE) {

        composable(route = ScreenVale.ScreenSplash.route) {
            BackHandler(true) {
                // Or do nothing
            }
            ScreenSplash(viewModel, navController = navController)
        }

        composable(route = ScreenVale.ScreenHome.route) {

            ScreenHome(viewModel,paddingValues)
        }
    }

}

const val ROOT_GRAPH_ROUTE = "root"

sealed class ScreenVale (open val route: String) {
    object ScreenHome : ScreenVale("home_screen")
    object ScreenSplash : ScreenVale("splash_screen")
}