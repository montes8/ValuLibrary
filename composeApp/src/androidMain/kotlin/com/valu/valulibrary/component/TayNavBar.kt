package com.valu.valulibrary.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController


open class TayNavigationItem(
    val route: String,
    val title: String,
    val icon: Int,
    val iconSelected: Int = 0,
    val iconCustom: Boolean = false
)

@Composable
fun TayCustomBottomBar(
    navController: NavHostController,
    items: List<TayNavigationItem>
) {

    val currentRoute = currentRoute(navController)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                drawIntoCanvas { canvas ->
                                    val paint = Paint()
                                    val frameworkPaint = paint.asFrameworkPaint()

                                    // Configuramos la sombra manualmente
                                    frameworkPaint.color = android.graphics.Color.WHITE // Color del fondo
                                    frameworkPaint.setShadowLayer(
                                        25f,     // Radio de difuminado (Blur)
                                        0f,      // Desplazamiento X
                                        -4f,    // Desplazamiento Y (Hacia ARRIBA)
                                        android.graphics.Color.argb(50, 0, 0, 0) // Color de sombra (negro suave)
                                    )

                                    // Dibujamos el rectángulo con la sombra
                                    canvas.drawRoundRect(
                                        0f, 0f, size.width, size.height,
                                        60f, 60f, // Radio de las esquinas
                                        paint
                                    )
                                }
                            }
                            .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            ,
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(vertical = 12.dp)
                                .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                                )
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items.forEach { item ->
                                val isSelected = currentRoute == item.route

                                CustomTabItem(
                                    item = item,
                                    isSelected = isSelected,
                                    onClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id){
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                        }
                                    }
                                )
                            }
                        }
                    }
}

@Composable
fun CustomTabItem(
    item: TayNavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {

        Column(modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = { onClick() }
            )
            },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = item.title,
                tint = if (isSelected) Color.Magenta else Color.Gray
            )
            Text(
                text = item.title,
                color = if (isSelected) Color.Magenta else Color.Gray,
                style = MaterialTheme.typography.labelLarge
            )
        }
}