package com.valu.valulibrary.ui.home


import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.gb.vale.uitaylibrarycompose.extra.UiTayCToolBar
import com.gb.vale.uitaylibrarycompose.model.UiTayToolBarModel
import com.valu.valulibrary.R
import com.valu.valulibrary.component.ColorStatusBar
import com.valu.valulibrary.component.TayCustomBottomBar
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.nav.NavigationNavBarHost
import com.valu.valulibrary.ui.nav.TayDestinations
import com.valu.valulibrary.utils.primaryAccent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScreenHome() {
    val viewModel : AppViewModel = koinViewModel()

    val navController = rememberNavController()
    //ColorStatusBar(color = primaryAccent, darkIcons = false)
    val navigationItems = listOf(
        TayDestinations.InitNavScreen,
        TayDestinations.ProductNavScreen,
        TayDestinations.MoreNavScreen,
    )
    viewModel.visibleToolbar = true

    Scaffold(
        topBar = {
            UiTayCToolBar(uiTayText = stringResource(R.string.tb_title_home), uiTayModifier = UiTayToolBarModel(
                uTBgColor = R.color.primary_secondary, uTTypeStart = false, uTTypeEnd = false
            )) {
            }
        }, // Aquí termina la TopBar
        bottomBar = {
            // Aquí va tu componente de barra inferior (ej. BottomAppBar o NavigationBar)
            TayCustomBottomBar(navController = navController,
                items = navigationItems,)

        }, content = { paddingValues ->
            NavigationNavBarHost(navController,paddingValues)
        }
    )



}