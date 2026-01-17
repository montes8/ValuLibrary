package com.valu.valulibrary.ui.home.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProductScreen(paddingValues: PaddingValues) {
    Column(modifier = Modifier.fillMaxSize().background(Color.Gray).padding(paddingValues)) {
        Text("soy los productos")
    }

}