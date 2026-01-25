package com.valu.valulibrary.component

import android.app.Activity
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.valu.valulibrary.R
import com.valu.valulibrary.model.CategoryModel
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.utils.TypographyTitleBold
import com.valu.valulibrary.utils.orange_400
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readRawBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.compose.koinInject
import java.util.concurrent.ConcurrentHashMap

@Composable
fun RecipesItem(model: Product){
    val client = koinInject<HttpClient>()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }.background(Color.White),
            // 1. Cambiamos a Alignment.Top para que todo empiece arriba
            verticalAlignment = Alignment.Top
        ) {
            ValeImage(
                url = getDirectDriveUrl("https://drive.google.com/file/d/1hp8ma3xqNWVq1LGnE9MOS1ndil7atKUS/view"),
                client = client,
                modifier = Modifier.size(120.dp)
            )

            // 2. La columna ahora distribuye su espacio
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp).padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text ="Alpha cuadriculado 70h",
                    maxLines = 1,
                    style = TypographyTitleBold.labelLarge,
                    color = orange_400
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Precio: S/ 4.00",
                    maxLines = 1,
                    style = TypographyTitleBold.labelSmall,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "cuaderno alpha cuadriculado de 70 hojas,con una tapa de cartón flexible de colores brillante, hojas resistentes.",
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 4,
                    style = MaterialTheme.typography.bodySmall,
                )

                Text(
                    text = "${model.sellerClient ?: "N/A"} ${model.district ?: "N/A"}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

        }
    }
}

@Composable
fun CategoryItem(category: CategoryModel,width : Dp,marginItem : Dp = 12.dp) {
    Column( modifier = Modifier
        .background(Color.White).padding(marginItem)) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(width / 2 - marginItem * 3)
                        .background(Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
                ) {
                    Image(painter = painterResource(id =
                        getIconCategory(category.uiId)),
                        contentDescription = "Descripción de la imagen",
                        modifier = Modifier.fillMaxSize() .background(Color.White),
                        contentScale = ContentScale.FillBounds)
                }

        Text(text = category.name,
            modifier = Modifier.fillMaxWidth().padding(4.dp)
            ,textAlign = TextAlign.Center,
            style = TypographyTitleBold.bodyMedium, color = orange_400
        )
    }

}

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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    // Solución para Android 9+ (API 28+)
                    val source = ImageDecoder.createSource(context.resources, resId)
                    drawable = ImageDecoder.decodeDrawable(source)
                } else {
                    // Solución para Android 7, 8 (API 24-27)
                    // Obtenemos el drawable de forma tradicional.
                    // Nota: Esto mostrará el primer frame del GIF como imagen estática.
                    val staticDrawable = androidx.core.content.ContextCompat.getDrawable(context, resId)
                    drawable = staticDrawable
                }
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
        modifier = modifier.background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        drawable?.let { gifDrawable ->
            AndroidView(
                modifier = sizeModifier,
                factory = { ctx ->
                    ImageView(ctx).apply {
                        scaleType = ImageView.ScaleType.CENTER_CROP
                        setImageDrawable(gifDrawable)

                        // Solo intentamos animar si la API lo permite
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            if (gifDrawable is AnimatedImageDrawable) {
                                gifDrawable.start()
                            }
                        }
                    }
                },
                update = { imageView ->
                    imageView.setImageDrawable(gifDrawable)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        if (gifDrawable is AnimatedImageDrawable) {
                            gifDrawable.start()
                        }
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

val Int.tayToDp: Dp
    @Composable
    get() = with(LocalDensity.current) { this@tayToDp.toDp() }



fun getIconCategory(id:Int):Int{
    return when(id){
        0 -> R.drawable.ic_notebook
        1 -> R.drawable.ic_bg_stationery
        2 -> R.drawable.ic_bg_writing
        3 -> R.drawable.ic_bg_geometry
        4 -> R.drawable.ic_bg_art
        5 -> R.drawable.ic_bg_stickers
        6 -> R.drawable.ic_bg_materials
        7 -> R.drawable.ic_bg_products
        8 -> R.drawable.ic_bg_additional
        else -> { R.drawable.ic_notebook}
    }
}


object ManualImageCache {
    // Guardamos las imágenes en un mapa para reutilizarlas
    private val cache = ConcurrentHashMap<String, ImageBitmap>()

    fun get(url: String): ImageBitmap? = cache[url]
    fun put(url: String, bitmap: ImageBitmap) { cache[url] = bitmap }
}


@Composable
fun ValeImage(
    url: String?,
    client: HttpClient,
    modifier: Modifier = Modifier
) {
    // Estado de la imagen
    var imageBitmap by remember(url) { mutableStateOf(url?.let { ManualImageCache.get(it) }) }
    var isLoading by remember(url) { mutableStateOf(imageBitmap == null) }

    // Efecto de carga
    LaunchedEffect(url) {
        if (url != null && imageBitmap == null) {
            isLoading = true
            try {
                val bytes = withContext(Dispatchers.IO) {
                    // 1. Usamos la función recomendada por Ktor
                    val response = client.get(url)
                    response.readRawBytes()
                }

                // 2. Decodificamos el array de bytes a un Bitmap nativo
                val nativeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)

                // 3. Verificamos que no sea nulo antes de convertirlo a ImageBitmap
                if (nativeBitmap != null) {
                    val bitmap = nativeBitmap.asImageBitmap()
                    ManualImageCache.put(url, bitmap)
                    imageBitmap = bitmap
                }
            } catch (e: Exception) {
                println("Error al descargar o decodificar imagen: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    // UI del componente
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when {
            imageBitmap != null -> {
                Image(
                    bitmap = imageBitmap!!,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            isLoading -> {
                // Puedes poner un CircularProgressIndicator aquí
                Box(Modifier.fillMaxSize().background(Color.LightGray.copy(alpha = 0.5f)))
            }
            else -> {
                // Icono de error
                Box(Modifier.fillMaxSize().background(Color.Gray))
            }
        }
    }
}

fun getDirectDriveUrl(originalUrl: String): String {
    // Si no es de Drive, la devolvemos tal cual
    if (!originalUrl.contains("drive.google.com")) return originalUrl

    // Buscamos el ID
    val idPattern = "/d/([^/]+)".toRegex()
    val match = idPattern.find(originalUrl)
    val id = match?.groupValues?.get(1)

    return if (id != null) {
        "https://drive.google.com/uc?export=view&id=$id"
    } else {
        originalUrl // Si falla, devolvemos la original
    }
}