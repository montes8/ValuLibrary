package com.valu.valulibrary.ui.detail

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valu.uitaycompose.extra.UiTayCToolBar
import com.valu.uitaycompose.model.UiTayToolBarModel
import com.valu.uitaycompose.utils.tay_deep_orange_300
import com.valu.valulibrary.component.ItemProduct
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ScreenDetail(id : String,name: String,viewModel: DetailViewModel = koinViewModel()) {

    val productsDetail = viewModel.listProductDetail
    LaunchedEffect(Unit) {
        viewModel.loadProductDetail(id)
    }

    Scaffold(
        topBar = {
            UiTayCToolBar(uiTayText = name, uiTayModifier = UiTayToolBarModel(
                uiBgColor = tay_deep_orange_300, uiTypeStart = false, uiTypeEnd = false)) {
            }
        }, content = { padding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(productsDetail) { product ->
                    ItemProduct(product)
                }
            }
        }
    )
}