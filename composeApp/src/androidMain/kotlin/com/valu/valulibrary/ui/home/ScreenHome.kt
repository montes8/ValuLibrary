package com.valu.valulibrary.ui.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.valu.valulibrary.component.TayCustomBottomBar
import com.valu.valulibrary.ui.nav.NavigationNavBarHost
import com.valu.valulibrary.ui.nav.TayDestinations


@Composable
fun ScreenHome(navControllerMain: NavHostController) {
    //val viewModel : AppViewModel = koinViewModel()
    val navController = rememberNavController()
    val navigationItems = listOf(
        TayDestinations.InitNavScreen,
        TayDestinations.ProductNavScreen,
        TayDestinations.MoreNavScreen,
    )

    Scaffold(
        topBar = {
           /* UiTayCToolBar(uiTayText = stringResource(R.string.tb_title_home), uiTayModifier = UiTayToolBarModel(
                uTBgColor = R.color.orange_300, uTTypeStart = false, uTTypeEnd = false
            )) {
            }*/
        }, // Aquí termina la TopBar
        bottomBar = {
            // Aquí va tu componente de barra inferior (ej. BottomAppBar o NavigationBar)
            TayCustomBottomBar(    navController
                ,navigationItems)

        }, content = { paddingValues ->
            NavigationNavBarHost(navController,navControllerMain)
        }
    )

}