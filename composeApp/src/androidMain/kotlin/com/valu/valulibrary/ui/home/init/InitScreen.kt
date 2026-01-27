package com.valu.valulibrary.ui.home.init

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valu.uitaycompose.swipe.UiTayMovieDrive
import com.valu.uitaycompose.utils.tay_deep_orange_400
import com.valu.uitaycompose.utils.tay_red_800
import com.valu.uitaycompose.utils.textGabbiB22
import com.valu.uitaycompose.utils.textM16
import com.valu.valulibrary.R
import com.valu.valulibrary.utils.htmlDriveMovie
import com.valu.valulibrary.utils.urlMovieDrive

@Composable
fun InitScreen(paddingValues: PaddingValues) {
    val scrollState = rememberScrollState()
    Column(modifier =
        Modifier.fillMaxSize().padding(paddingValues).background(Color.White).verticalScroll(scrollState)) {
        Box(modifier = Modifier.padding(16.dp)){
            UiTayMovieDrive(urlMovieDrive.htmlDriveMovie())
        }
        Column(modifier =
            Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text(stringResource(R.string.tb_title_home), modifier =
                Modifier.padding(top = 4.dp),
                style = textGabbiB22, color = tay_deep_orange_400
            )
            Text(stringResource(R.string.text_description),modifier =
                Modifier.padding(top = 8.dp),
                style = textM16,
                color = Color.Black)
            Text(stringResource(R.string.text_note),modifier =
                Modifier.padding(top = 8.dp),
                style = textM16,
                color = tay_red_800
            )

        }
    }
}