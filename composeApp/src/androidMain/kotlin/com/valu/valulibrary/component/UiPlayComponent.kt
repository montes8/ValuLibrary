package com.valu.valulibrary.component

import android.app.Activity
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun YouTubePlayerLibreria(videoId: String) {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                // Añadimos el listener para cargar el video cuando esté listo
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }
                })
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun VideoYoutubeWebView(videoHtml: String,height: Dp = 200.dp) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                // 1. FUNDAMENTAL para video
                setLayerType(View.LAYER_TYPE_HARDWARE, null)
                settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                webViewClient = WebViewClient() // Asegura que el contenido se cargue aquí
                webChromeClient = WebChromeClient()

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    mediaPlaybackRequiresUserGesture = false
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }

                loadDataWithBaseURL(
                    "https://www.youtube.com",
                    videoHtml,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        modifier = Modifier.fillMaxWidth().height(height)
    )
}
@Composable
fun VideoWebView(videoHtml: String,height: Dp = 200.dp) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                setLayerType(View.LAYER_TYPE_HARDWARE, null)

                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }

                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()

                loadDataWithBaseURL(
                    "https://drive.google.com",
                    videoHtml,
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        modifier = Modifier.fillMaxWidth().height(height).clip(RoundedCornerShape(12.dp))
    )
}
@Composable
fun ValeGif(
    resId: Int,
    width: Dp? = null,
    height: Dp? = null,
    backgroundColor: Color = Color.Transparent,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var drawable by remember { mutableStateOf<Drawable?>(null) }

    LaunchedEffect(resId) {
        withContext(Dispatchers.IO) {
            try {
                val source = ImageDecoder.createSource(context.resources, resId)
                val decoded = ImageDecoder.decodeDrawable(source)
                drawable = decoded
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    val sizeModifier = if (width != null && height != null) {
        Modifier.size(width, height)
    } else {
        Modifier.fillMaxSize()
    }

    Box(
        modifier = modifier
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        drawable?.let { gifDrawable ->
            AndroidView(
                modifier = sizeModifier,
                factory = { ctx ->
                    ImageView(ctx).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        setImageDrawable(gifDrawable)
                        post {
                            if (gifDrawable is AnimatedImageDrawable) {
                                gifDrawable.start()
                            }
                        }
                    }
                },
                update = { imageView ->
                    imageView.setImageDrawable(gifDrawable)
                    if (gifDrawable is AnimatedImageDrawable) {
                        gifDrawable.start()
                    }
                }
            )
        }
    }
}


@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}


@Composable
fun ColorStatusBar(color: Color, darkIcons: Boolean) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkIcons
        }
    }
}