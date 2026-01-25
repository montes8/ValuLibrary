package com.valu.valulibrary.ui.home.init

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.valu.valulibrary.component.VideoWebView
import com.valu.valulibrary.ui.nav.ScreenVale
import com.valu.valulibrary.utils.TypographyTitleBold
import com.valu.valulibrary.utils.orange_400

@Composable
fun InitScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val videoHtml = """
        <html>
            <body style="margin:0;padding:0;background-color:black;">
                <iframe 
                    src="https://drive.google.com/file/d/14C5wUiVuZgx9jT57WSU7NX6_4QF1LKj3/preview" 
                    width="100%" 
                    height="100%" 
                    frameborder="0" 
                    allow="autoplay; encrypted-media" 
                    allowfullscreen>
                </iframe>
            </body>
        </html>
    """.trimIndent()

    val descripcionLibreria = """
    🎒 ¡Arma tu mochila con lo mejor!
    Si necesitas lo básico para el colegio, ¡aquí lo tenemos! 
    Tenemos útiles que no fallan para tus tareas:
    • Lápices y lapiceros suaves.
    • Cuadernos rayados y cuadriculados.
    • Borradores que no manchan y tajadores con punta fina.
    • Reglas, tijeras y goma en barra.
    • Colores brillantes y resaltadores.
    ¡Ven por lo que te falta! ✨
    """.trimIndent()

    val import = """
    ¡Importante!
    • Los precios sin internet son referenciales.
     """.trimIndent()
    Column(modifier =
        Modifier.fillMaxSize().background(Color.White).verticalScroll(scrollState)) {
        Text("Libreria VALU",modifier =
            Modifier.padding(top = 12.dp).fillMaxWidth(),textAlign= TextAlign.Center,
            style = TypographyTitleBold.titleSmall, color = orange_400
        )
        Box(modifier = Modifier.padding(16.dp)){
            VideoWebView(videoHtml)
        }
        Column(modifier =
            Modifier.padding(start = 16.dp, end = 16.dp)) {
            Text("Libreria Valu te da bienvenida", modifier =
                Modifier.padding(top = 4.dp),color = Color.Black)
            Text(descripcionLibreria,color = Color.Black)
            Text(import,modifier =
                Modifier.padding(top = 8.dp),color = Color.Cyan)
            Text("Terminos y condiciones",modifier =
                Modifier.padding(top = 4.dp).clickable{
                    navController.navigate(ScreenVale.ScreenTerm)
                },color = Color.Blue,

                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                ))
            Text("Sobre nostros",color = Color.Blue,
                modifier = Modifier.clickable{
                    navController.navigate(ScreenVale.ScreenAbout)

                },
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}