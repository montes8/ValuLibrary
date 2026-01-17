package com.valu.valulibrary.ui.nav

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valu.valulibrary.R
import com.valu.valulibrary.component.TayNavigationItem
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.home.ScreenHome
import com.valu.valulibrary.ui.home.init.InitScreen
import com.valu.valulibrary.ui.home.more.MoresScreen
import com.valu.valulibrary.ui.home.product.ProductScreen

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
        }

        composable(route = ScreenVale.ScreenHome.route) {

            ScreenHome()
        }
    }

}



const val ROOT_GRAPH_ROUTE = "root"

sealed class ScreenVale (open val route: String) {
    object ScreenHome : ScreenVale("home_screen")
    object ScreenSplash : ScreenVale("splash_screen")
}

@Composable
fun NavigationNavBarHost(
    navController: NavHostController = rememberNavController(),paddingValues:PaddingValues
) {
    NavHost(navController = navController, startDestination = Destinations.InitNavScreen.route,
        exitTransition = {
            ExitTransition.None
        }) {
        composable(Destinations.InitNavScreen.route) { InitScreen(paddingValues)}
        composable(Destinations.ProductNavScreen.route) { ProductScreen(paddingValues)}
        composable(Destinations.MoreNavScreen.route) {MoresScreen(paddingValues) }
    }
}

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: Int,
    val iconSelected: Int = 0,
    val iconCustom: Boolean = false
) {
    data object InitNavScreen: Destinations("init_screen", "Inicio", R.drawable.ic_nav_init)
    data object ProductNavScreen: Destinations("product_screen", "Utiles", R.drawable.ic_nav_product)
    data object MoreNavScreen: Destinations("more_screen", "Mas", R.drawable.ic_nav_more)


}


sealed class TayDestinations(
    val route: String,
    val title: String,
    val icon: Int,
    val iconSelected: Int = 0,
    val iconCustom: Boolean = false
) {
    data object InitNavScreen: TayNavigationItem("init_screen", "Inicio", R.drawable.ic_nav_init)
    data object ProductNavScreen: TayNavigationItem("product_screen", "Utiles", R.drawable.ic_nav_product)
    data object MoreNavScreen: TayNavigationItem("more_screen", "Mas", R.drawable.ic_nav_more)


}
