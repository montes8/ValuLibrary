package com.valu.valulibrary.ui.splash

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.pierfrancescosoffritti.androidyoutubeplayer.BuildConfig
import com.valu.uitaycompose.swipe.UiTayGif
import com.valu.uitaycompose.utils.tay_deep_orange_400
import com.valu.uitaycompose.utils.textGabbi16
import com.valu.uitaycompose.utils.textGabbiB30
import com.valu.valulibrary.R
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.home.HomeActivity
import com.valu.valulibrary.utils.versionApp
import kotlinx.coroutines.flow.collectLatest

/** todo LaunchedEffect(key1 = true) {
         viewModel.eventFlow.collectLatest { event ->
              when (event) {
                  is InitUiEvent.NavigateToNext -> {
                      HomeActivity.newInstance(context)
                      context.finish()
                  }
                  else ->  {} }}} */

@SuppressLint("ContextCastToActivity")
@Composable
fun ScreenMySplash(viewModel: AppViewModel) {
    val context = LocalContext.current as Activity
    val startAnimation = remember { mutableStateOf(false) }

    val offsetTop by animateDpAsState(
        targetValue = if (startAnimation.value) 0.dp else (-150).dp,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "LogoAnimation"
    )

    val offsetBottom by animateDpAsState(
        targetValue = if (startAnimation.value) 0.dp else 150.dp,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
        label = "TextAnimation"
    )

    val opacity by animateFloatAsState(
        targetValue = if (startAnimation.value) 1f else 0f,
        animationSpec = tween(durationMillis = 800),
        label = "FadeIn"
    )

    LaunchedEffect(Unit) {
        startAnimation.value = true
        viewModel.loadValidateLogin()
        viewModel.eventFlow.collectLatest {
            HomeActivity.newInstance(context)
            context.finish()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .paint(
                painterResource(id = R.drawable.ic_bc_splash),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UiTayGif(
                resId = R.drawable.gif_splash,
                width = 150.dp,
                height = 150.dp,
                modifier = Modifier
                    .offset(y = offsetTop)
                    .graphicsLayer(alpha = opacity)
            )

            Column(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .offset(y = offsetBottom)
                    .graphicsLayer(alpha = opacity),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.text_title_splash),
                    textAlign = TextAlign.Center,
                    style = textGabbiB30,
                    color = tay_deep_orange_400
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = stringResource(R.string.text_sub_title_splash),
                    textAlign = TextAlign.Center,
                    style = textGabbiB30,
                    color = tay_deep_orange_400
                )

                Spacer(modifier = Modifier.height(30.dp))

                Image(
                    painter = painterResource(R.drawable.ic_splash_botton),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp).height(40.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Text(
            text = "version: ${context.versionApp()}",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            style = textGabbi16,
            color = Color.Black
        )
    }
}
