package com.valu.valulibrary.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valu.uitaycompose.swipe.UiTayGif
import com.valu.uitaycompose.utils.tay_deep_orange_400
import com.valu.uitaycompose.utils.textGabbi16
import com.valu.uitaycompose.utils.textGabbiB30
import com.valu.valulibrary.R
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.home.HomeActivity
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("ContextCastToActivity")
@Composable
fun ScreenMySplash(viewModel: AppViewModel) {
    val context = LocalContext.current as Activity
    var animLotti by remember { mutableStateOf(false) }
    var animText by remember { mutableStateOf(false) }
    val offset by animateDpAsState(
        targetValue = if (animLotti) 0.dp else (-500).dp,
        animationSpec = tween(
            durationMillis = 2500,
            easing = LinearEasing
        ),
        label = "Animation top"
    )

    val offsetBottom by animateDpAsState(
        targetValue = if (animText) (-30).dp else (500).dp,
        animationSpec = tween(
            durationMillis = 1500,
            easing = LinearEasing
        ),
        label = "Animation bottom"
    )

    LaunchedEffect(Unit) {
        animText = true
        animLotti = true
        viewModel.loadValidateLogin()
    }
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is InitUiEvent.NavigateToNext -> {
                    HomeActivity.newInstance(context)
                    context.finish()
                }
                else ->  {} }

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.ic_bc_splash), contentScale
                = ContentScale.FillBounds
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box( modifier = Modifier
            .offset(y = offset)
            .graphicsLayer()) {
            Box( modifier = Modifier
                .offset(y = offset)
                .graphicsLayer()) {
                UiTayGif(
                    resId = R.drawable.gif_splash,
                     width =  150.dp,
                     height = 150.dp
                )
            }
        }

        Column(modifier = Modifier.padding(top = 40.dp).padding(start = 30.dp)
            .offset(y = offsetBottom)
            .graphicsLayer(),horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.text_title_splash),
                textAlign = TextAlign.Center,
                style = textGabbiB30,
                color = tay_deep_orange_400
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                color = tay_deep_orange_400,
                text = stringResource(R.string.text_sub_title_splash),
                textAlign = TextAlign.Center,
                style = textGabbiB30

                )
            Spacer(
                modifier =  Modifier.height(40.dp)
            )
            Image(
                painterResource(R.drawable.ic_splash_botton),
                modifier = Modifier.width(200.dp).height(40.dp),
                alignment =  Alignment.Center,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Box(contentAlignment= Alignment.BottomEnd,) {
            Text(
                modifier = Modifier
                    .fillMaxWidth().padding(end = 8.dp),
                color = Color.Black,
                text = "version 1.0",
                textAlign = TextAlign.Center,
                style = textGabbi16
            )
        }
    }
}
