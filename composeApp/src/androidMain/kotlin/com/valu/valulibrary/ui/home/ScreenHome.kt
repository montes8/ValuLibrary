package com.valu.valulibrary.ui.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.valu.uitaycompose.extra.UiTayCToolBar
import com.valu.uitaycompose.model.UiToolBarModel
import com.valu.uitaycompose.utils.tay_deep_orange_200
import com.valu.valulibrary.R
import com.valu.valulibrary.component.TayCustomBottomBar
import com.valu.valulibrary.model.TaySessionData
import com.valu.valulibrary.ui.nav.NavigationNavBarHost
import com.valu.valulibrary.ui.nav.TayDestinations

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenHome(navControllerMain: NavHostController) {
    val navController = rememberNavController()
    var items = listOf(
        TayDestinations.InitNavScreen,
        TayDestinations.ProductNavScreen,
        TayDestinations.ImportsScreenNavScreen,
        TayDestinations.MoreNavScreen,
    )

    val mutableNavigationItems = items.toMutableList()
    if(!TaySessionData.enableUtils()){
        mutableNavigationItems.removeAt(1)
    }

    if(!TaySessionData.enableImports()){
        mutableNavigationItems.removeAt(2)
    }

    Scaffold(
        topBar = {
            UiTayCToolBar(uiTayText = stringResource(R.string.tb_title_home), uiTayModifier = UiToolBarModel(
                uiBgColor = tay_deep_orange_200, uiTypeStart = false, uiTypeEnd = false
            )
            ) {
            }
        },
        bottomBar = {
            TayCustomBottomBar(    navController
                ,mutableNavigationItems)

        }, content = { paddingValues ->
            NavigationNavBarHost(navController,navControllerMain,paddingValues)
        }
    )

}