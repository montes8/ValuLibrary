package com.valu.valulibrary.ui.home.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.valu.valulibrary.component.UtilEscolarItem
import com.valu.valulibrary.component.tayToDp
import com.valu.valulibrary.model.ProductModel

@Composable
fun ProductScreen(paddingValues: PaddingValues) {

    var columnWidthPx by remember { mutableStateOf(0) }


    val utiles = listOf(ProductModel(), ProductModel(),ProductModel(), ProductModel(), ProductModel(), ProductModel())
    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White)
        .padding(paddingValues).onGloballyPositioned { coordinates ->
            columnWidthPx = coordinates.size.width
        }) {

        Column() {
            Text("Utiles mas pedidos",modifier =
                Modifier.padding(16.dp))
            LazyVerticalGrid(
                // 'Fixed(2)' define exactamente 2 columnas
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp) // Margen en los bordes de la pantalla
            ) {
                items(utiles) { util ->
                    UtilEscolarItem(util,columnWidthPx.tayToDp)
                }
            }
        }

    }

}