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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.valu.valulibrary.R
import com.valu.valulibrary.ui.nav.TayDestinations

val navigationItems = listOf(
    TayNavigationItem(TayDestinations.InitNavScreen,"Inicio",R.drawable.ic_nav_init),
    TayNavigationItem(TayDestinations.ProductNavScreen,"Productos",R.drawable.ic_nav_product),
    TayNavigationItem(TayDestinations.MoreNavScreen,"categorias",R.drawable.ic_nav_category),
)
open class TayNavigationItem(
    val route: TayDestinations,
    val title: String,
    val icon: Int,
    val iconSelected: Int = 0,
    val iconCustom: Boolean = false
)

@Composable
fun TayCustomBottomBar(
    backStack: NavBackStack<TayDestinations>,
    items: List<TayNavigationItem>
) {
    val currentKey = backStack.lastOrNull()
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
                                val isSelected = currentKey == item.route
                                CustomTabItem(
                                    item = item,
                                    isSelected = isSelected,
                                    onClick = {
                                        if (!isSelected) {
                                            backStack.add(item.route)
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