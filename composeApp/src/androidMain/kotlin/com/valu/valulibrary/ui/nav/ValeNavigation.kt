package com.valu.valulibrary.ui.nav

import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.valu.valulibrary.R
import com.valu.valulibrary.ui.about.ScreenAboutUs
import com.valu.valulibrary.ui.detail.ScreenDetail
import com.valu.valulibrary.ui.home.ScreenHome
import com.valu.valulibrary.ui.home.init.InitScreen
import com.valu.valulibrary.ui.home.more.MoresScreen
import com.valu.valulibrary.ui.home.product.ProductScreen
import com.valu.valulibrary.ui.term.ScreenTerm

@Composable
fun ValeNavigationMain(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = ScreenVale.HomeScreen.route,
        exitTransition = {
            ExitTransition.None
        }) {
        composable(ScreenVale.HomeScreen.route) { ScreenHome(navController)}
        composable(ScreenVale.ScreenTerm.route) { ScreenTerm()}
        composable(ScreenVale.ScreenAbout.route) { ScreenAboutUs()}
        composable(ScreenVale.ScreenDetail.route) { ScreenDetail()}
    }
}



sealed class ScreenVale(
    val route: String
) {
    data object HomeScreen: ScreenVale("home_screen")
    data object ScreenTerm: ScreenVale("term_screen")
    data object ScreenAbout: ScreenVale("about_screen")
    data object ScreenDetail: ScreenVale("detail_screen")
}

sealed class TayDestinations(
    val route: String,
    val title: String,
    val icon: Int,
    val iconSelected: Int = 0
) {
    data object InitNavScreen: TayDestinations("init_screen","Inicio",R.drawable.ic_nav_init)
    data object ProductNavScreen: TayDestinations("product_screen","Productos",R.drawable.ic_nav_product)
    data object MoreNavScreen: TayDestinations("more_screen","categorias",R.drawable.ic_nav_category)
}

@Composable
fun NavigationNavBarHost(  navController: NavHostController = rememberNavController(),
                           navControllerMain: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = TayDestinations.InitNavScreen.route,
        exitTransition = {
            ExitTransition.None
        }) {
        composable(TayDestinations.InitNavScreen.route) { InitScreen(navControllerMain)}
        composable(TayDestinations.ProductNavScreen.route) { ProductScreen()}
        composable(TayDestinations.MoreNavScreen.route) {MoresScreen(navControllerMain) }
    }
}


