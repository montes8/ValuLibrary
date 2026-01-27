package com.valu.valulibrary.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.valu.uitaycompose.extra.UiTayCToolBar
import com.valu.uitaycompose.model.UiTayToolBarModel
import com.valu.uitaycompose.utils.tay_deep_orange_300
import com.valu.valulibrary.R
import com.valu.valulibrary.component.TayCustomBottomBar
import com.valu.valulibrary.ui.nav.NavigationNavBarHost
import com.valu.valulibrary.ui.nav.TayDestinations

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenHome(navControllerMain: NavHostController) {
    val navController = rememberNavController()
    val navigationItems = listOf(
        TayDestinations.InitNavScreen,
        TayDestinations.ProductNavScreen,
        TayDestinations.MoreNavScreen,
    )

    Scaffold(
        topBar = {
            UiTayCToolBar(uiTayText = stringResource(R.string.tb_title_home), uiTayModifier = UiTayToolBarModel(
                uiBgColor = tay_deep_orange_300, uiTypeStart = false, uiTypeEnd = false)) {
            }
        },
        bottomBar = {
            TayCustomBottomBar(    navController
                ,navigationItems)

        }, content = { paddingValues ->
            NavigationNavBarHost(navController,navControllerMain,paddingValues)
        }
    )

}