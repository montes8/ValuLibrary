package com.valu.valulibrary.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.valu.uitaycompose.utils.tay_deep_orange_400
import com.valu.uitaycompose.utils.tay_grey_300
import com.valu.uitaycompose.utils.tay_grey_400
import com.valu.uitaycompose.utils.textB12
import com.valu.uitaycompose.utils.textB14
import com.valu.uitaycompose.utils.textS12
import com.valu.uitaycompose.utils.textS14
import com.valu.valulibrary.ui.nav.TayDestinations
@SuppressLint("SuspiciousIndentation")
@Composable
fun TayCustomBottomBar(
    navController: NavHostController,
    items: List<TayDestinations>
) {
    val currentRoute = currentRoute(navController)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                drawIntoCanvas { canvas ->
                                    val paint = Paint()
                                    val frameworkPaint = paint.asFrameworkPaint()
                                    frameworkPaint.color = android.graphics.Color.WHITE
                                    frameworkPaint.setShadowLayer(
                                        25f,
                                        0f,
                                        -4f,
                                        android.graphics.Color.argb(50, 0, 0, 0) // Color de sombra (negro suave)
                                    )
                                    canvas.drawRoundRect(
                                        0f, 0f, size.width, size.height,
                                        60f, 60f,
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
    item: TayDestinations,
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
                tint = if (isSelected) tay_deep_orange_400 else tay_grey_400
            )
            Text(
                text = item.title,
                color = if (isSelected) tay_deep_orange_400 else tay_grey_400,
                style = textS12
            )
        }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}