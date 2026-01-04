package com.valu.valulibrary.ui.nav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable

@Composable
fun ValeNavigation(paddingValues: PaddingValues) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        route = ROOT_GRAPH_ROUTE) {

        composable(route = Screen.SplashScreen.route) {
            BackHandler(true) {
                // Or do nothing
            }
            ScreenSplash(viewModel, navController = navController)
        }

        composable(route = Screen.HomeScreen.route) {
            BackHandler(true) {
                // Or do nothing
            }
            ScreenHome(viewModel,paddingValues)
        }
    }

}