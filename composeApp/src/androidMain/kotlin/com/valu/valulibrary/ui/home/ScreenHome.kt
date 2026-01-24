package com.valu.valulibrary.ui.home


import android.os.Parcelable
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import com.valu.valulibrary.R
import com.valu.valulibrary.component.TayCustomBottomBar
import com.valu.valulibrary.component.TayNavigationItem
import com.valu.valulibrary.component.navigationItems
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.nav.NavigationNavBarHost
import com.valu.valulibrary.ui.nav.TayDestinations
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ScreenHome() {
    //val viewModel : AppViewModel = koinViewModel()
    val backStack = rememberNavBackStack(TayDestinations.InitNavScreen) as NavBackStack<TayDestinations>    //ColorStatusBar(color = primaryAccent, darkIcons = false)

    //viewModel.visibleToolbar = true

    Scaffold(
        topBar = {
           /* UiTayCToolBar(uiTayText = stringResource(R.string.tb_title_home), uiTayModifier = UiTayToolBarModel(
                uTBgColor = R.color.orange_300, uTTypeStart = false, uTTypeEnd = false
            )) {
            }*/
        }, // Aquí termina la TopBar
        bottomBar = {
            // Aquí va tu componente de barra inferior (ej. BottomAppBar o NavigationBar)
            TayCustomBottomBar(backStack = backStack,
                items = navigationItems)

        }, content = { paddingValues ->
            NavigationNavBarHost(backStack,paddingValues)
        }
    )

}