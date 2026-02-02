package com.valu.valulibrary.ui.nav

import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.valu.valulibrary.R
import com.valu.valulibrary.ui.detail.ScreenDetail
import com.valu.valulibrary.ui.home.ScreenHome
import com.valu.valulibrary.ui.home.init.InitScreen
import com.valu.valulibrary.ui.home.more.MoresScreen
import com.valu.valulibrary.ui.home.product.ProductScreen
import kotlinx.serialization.Serializable

@Composable
fun ValeNavigationMain(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = ScreenVale.HomeScreen,
        exitTransition = { ExitTransition.None }
    ) {
        composable<ScreenVale.HomeScreen> {
            ScreenHome(navController)
        }

        composable<ScreenVale.ScreenDetail> { backStackEntry ->
            val detailArgs = backStackEntry.toRoute<ScreenVale.ScreenDetail>()

            ScreenDetail(id = detailArgs.categoryId, name = detailArgs.name)
        }
    }
}


@Serializable
sealed class ScreenVale(
) {
    @Serializable
    data object HomeScreen: ScreenVale()
    @Serializable
    data class ScreenDetail(val categoryId: String, val name: String): ScreenVale()
}

@Serializable
sealed interface TayRoute {
    @Serializable object Init : TayRoute
    @Serializable object Product : TayRoute
    @Serializable object More : TayRoute
}

sealed class TayDestinations(
    val route: TayRoute,
    val title: String,
    val icon: Int,
    val iconSelected: Int = 0
) {
    data object InitNavScreen: TayDestinations(TayRoute.Init,"Inicio",R.drawable.ic_nav_init)
    data object ProductNavScreen: TayDestinations(TayRoute.Product,"Productos",R.drawable.ic_nav_product)
    data object MoreNavScreen: TayDestinations(TayRoute.More,"categorias",R.drawable.ic_nav_category)
}

@Composable
fun NavigationNavBarHost(  navController: NavHostController = rememberNavController(),
                           navControllerMain: NavHostController = rememberNavController(),paddingValues: PaddingValues
) {

    NavHost(navController = navController, startDestination = TayRoute.Init,
        exitTransition = {
            ExitTransition.None
        }) {
        composable<TayRoute.Init> { InitScreen(paddingValues)}
        composable<TayRoute.Product> { ProductScreen(paddingValues)}
        composable<TayRoute.More>  {MoresScreen(navControllerMain,paddingValues) }
    }
}


