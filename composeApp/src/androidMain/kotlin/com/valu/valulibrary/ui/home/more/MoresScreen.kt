package com.valu.valulibrary.ui.home.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.valu.uitaycompose.utils.UI_EMPTY
import com.valu.uitaycompose.utils.tay_deep_orange_400
import com.valu.uitaycompose.utils.textGabbiB25
import com.valu.valulibrary.component.CategoryItem
import com.valu.valulibrary.component.tayToDp
import com.valu.valulibrary.model.TaySessionData
import com.valu.valulibrary.ui.nav.ScreenVale

@Composable
fun MoresScreen (navController: NavHostController,paddingValues: PaddingValues) {
    var columnWidthPx by remember { mutableStateOf(0) }

    val items = TaySessionData.categories
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White).padding(paddingValues)
        .onGloballyPositioned { coordinates ->
        columnWidthPx = coordinates.size.width
        }) {

        Text("Buscalo por Categorias",modifier =
            Modifier.padding(top = 12.dp).fillMaxWidth(),textAlign= TextAlign.Center,
            style = textGabbiB25, color = tay_deep_orange_400
        )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(items) { util ->
                    CategoryItem(util,columnWidthPx.tayToDp){ value ->
                        navController.navigate(ScreenVale.ScreenDetail(value.identifier.toString(),value.name?: UI_EMPTY))
                    }
                }
            }

    }

}