package com.valu.valulibrary.ui.nav

import android.os.Parcelable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.valu.valulibrary.ui.about.ScreenAboutUs
import com.valu.valulibrary.ui.detail.ScreenDetail
import com.valu.valulibrary.ui.home.ScreenHome
import com.valu.valulibrary.ui.home.init.InitScreen
import com.valu.valulibrary.ui.home.more.MoresScreen
import com.valu.valulibrary.ui.home.product.ProductScreen
import com.valu.valulibrary.ui.term.ScreenTerm
import kotlinx.serialization.Serializable
import kotlinx.parcelize.Parcelize

@Composable
fun ValeNavigationMain() {
    val backStack = rememberNavBackStack(ScreenVale.ScreenHome)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.back() },
        entryProvider = entryProvider {
            entry<ScreenVale.ScreenHome> {
                ScreenHome()
            }
            entry<ScreenVale.ScreenTerm> {
                ScreenTerm()
            }
            entry<ScreenVale.ScreenAbout> {
                ScreenAboutUs()
            }

            entry<ScreenVale.ScreenDetail> {
                ScreenDetail()
            }
        }
    )
}

@Serializable
sealed class ScreenVale  : NavKey {
    @Serializable object ScreenHome : ScreenVale()
    @Serializable object ScreenTerm : ScreenVale()
    @Serializable object ScreenAbout : ScreenVale()
    @Serializable object ScreenDetail : ScreenVale()
}


@Serializable
sealed class TayDestinations : NavKey , Parcelable {
    @Serializable @Parcelize data object InitNavScreen: TayDestinations( )
    @Serializable @Parcelize data object ProductNavScreen: TayDestinations()
    @Serializable @Parcelize data object MoreNavScreen: TayDestinations()
}
@Composable
fun NavigationNavBarHost(backStack: NavBackStack<TayDestinations>, paddingValues:PaddingValues) {
    NavDisplay(
        backStack = backStack,
        onBack = { },
        entryProvider = entryProvider {
            entry<TayDestinations.InitNavScreen> {
                InitScreen(paddingValues)
            }
            entry<TayDestinations.ProductNavScreen> { key ->
                ProductScreen()
            }
            entry<TayDestinations.MoreNavScreen> {
                MoresScreen(paddingValues)
            }
        }
    )
}



fun NavBackStack<NavKey>.navigateNext(screen: NavKey) {
    add(screen)
}

fun NavBackStack<NavKey>.back() {
    if (isEmpty()) return
    removeLastOrNull()
}

fun NavBackStack<NavKey>.backCustom(screen: NavKey) {
    if (isEmpty()) return
    if(screen !in this) return

    while(isNotEmpty() && last() != screen){
        removeLastOrNull()
    }

}

