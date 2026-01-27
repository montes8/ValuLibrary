package com.valu.valulibrary.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.valu.uitaycompose.swipe.UiTayUrlImage
import com.valu.uitaycompose.utils.extension.uiTayDriveUrl
import com.valu.uitaycompose.utils.tay_deep_orange_400
import com.valu.uitaycompose.utils.tay_orange_400
import com.valu.uitaycompose.utils.textGabbi12
import com.valu.uitaycompose.utils.textGabbi14
import com.valu.uitaycompose.utils.textGabbi16
import com.valu.uitaycompose.utils.textGabbi18
import com.valu.uitaycompose.utils.textGabbiB16
import com.valu.uitaycompose.utils.textGabbiB18
import com.valu.uitaycompose.utils.textGabbiB20
import com.valu.uitaycompose.utils.textM10
import com.valu.uitaycompose.utils.textM12
import com.valu.uitaycompose.utils.textM14
import com.valu.uitaycompose.utils.textS14
import com.valu.uitaycompose.utils.textS16
import com.valu.uitaycompose.utils.textSe14
import com.valu.uitaycompose.utils.textSe16
import com.valu.valulibrary.R
import com.valu.valulibrary.model.CategoryModel
import com.valu.valulibrary.model.Product

@Composable
fun ItemProduct(model: Product){
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
                .clickable {
                }.background(Color.White),
            verticalAlignment = Alignment.Top
        ) {
            UiTayUrlImage(
                url = uiTayDriveUrl("https://drive.google.com/file/d/1hp8ma3xqNWVq1LGnE9MOS1ndil7atKUS/view"),
                modifier= Modifier.width(120.dp).height(120.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp).padding(top = 6.dp, start = 6.dp, end = 6.dp, bottom = 4.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text ="Alpha cuadriculado 70h",
                    maxLines = 1,
                    style = textGabbiB16,
                    color = tay_deep_orange_400
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Precio: S/ 4.00",
                    maxLines = 1,
                    style = textS14,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "cuaderno alpha cuadriculado de 70 hojas,con una tapa de cartón flexible de colores brillante, hojas resistentes.",
                    modifier = Modifier
                        .weight(1f),
                    maxLines = 4,
                    style = textM12,
                    color = Color.Black
                )

                Text(
                    text = "${model.sellerClient ?: "N/A"} ${model.district ?: "N/A"}",
                    style = textM12,
                    color = Color.Gray
                )
            }

        }
    }
}

@Composable
fun CategoryItem(
    category: CategoryModel, width: Dp, marginItem: Dp = 12.dp,
    onItemClick: (CategoryModel) -> Unit
) {
    Column( modifier = Modifier
        .background(Color.White).padding(marginItem).clickable{
            onItemClick.invoke(category)
        }) {
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
            style =textGabbiB20, color = tay_orange_400
        )
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

