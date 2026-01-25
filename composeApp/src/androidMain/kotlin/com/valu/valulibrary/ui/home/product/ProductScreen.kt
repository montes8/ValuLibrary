package com.valu.valulibrary.ui.home.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valu.valulibrary.component.RecipesItem
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.utils.TypographySubTitleGabbi
import com.valu.valulibrary.utils.TypographyTitleBold
import com.valu.valulibrary.utils.orange_400
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductScreen(viewModel: AppViewModel = koinViewModel()) {


    val products = viewModel.listProducts
    LaunchedEffect(Unit) {
        viewModel.loadProduct()
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White)) {

        Column() {
            Text("Utiles mas pedidos",modifier =
                Modifier.padding(top = 12.dp).fillMaxWidth(),textAlign= TextAlign.Center,
                style = TypographyTitleBold.titleSmall, color = orange_400
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(products) { product ->
                    RecipesItem(product)
                }
            }
        }

    }

}