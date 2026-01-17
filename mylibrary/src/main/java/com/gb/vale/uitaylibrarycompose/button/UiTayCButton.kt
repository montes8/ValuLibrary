package com.gb.vale.uitaylibrarycompose.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gb.vale.uitaylibrarycompose.R
import com.gb.vale.uitaylibrarycompose.model.UTStyleCButton
import com.gb.vale.uitaylibrarycompose.model.UiTayButtonModel
import com.gb.vale.uitaylibrarycompose.model.UiTaySwitchModel
import com.gb.vale.uitaylibrarycompose.model.uiTayBackground
import com.gb.vale.uitaylibrarycompose.model.uiTayStroke
import com.gb.vale.uitaylibrarycompose.model.uiTayTextColor
import com.gb.vale.uitaylibrarycompose.model.utBtnState
import com.gb.vale.uitaylibrarycompose.utils.UI_TAY_TEXT_DEFAULT
import com.gb.vale.uitaylibrarycompose.utils.uiTayNoRippleClickable


@Composable
fun UiTayCButton(uiTayText : String = UI_TAY_TEXT_DEFAULT, uiTayEnable : Boolean = false,
                 uiTayStyleBtn : UTStyleCButton = UTStyleCButton.UI_TAY_PRIMARY,
                 uiTayBtnModel : UiTayButtonModel = UiTayButtonModel(),
                 uiTayClick: () -> Unit
) {
    var selected by remember { mutableStateOf(false) }

    Button(modifier = Modifier
        .height(uiTayBtnModel.uTHeight.dp).fillMaxWidth(),
        shape = RoundedCornerShape(uiTayBtnModel.uTRadius.dp),
        border = BorderStroke(uiTayBtnModel.uTStrokeWith.dp, colorResource(id =
        uiTayBtnModel.uiTayStroke(uiTayStyleBtn,uiTayEnable.utBtnState()))),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id =
        uiTayBtnModel.uiTayBackground(uiTayStyleBtn,uiTayEnable.utBtnState())),
            contentColor =  colorResource(id =
                uiTayBtnModel.uiTayBackground(uiTayStyleBtn,uiTayEnable.utBtnState())
            )),
        onClick = {
           if(uiTayEnable)uiTayClick()
        }) {
        Text(text = uiTayText, color = colorResource(id =
         uiTayBtnModel.uiTayTextColor(uiTayStyleBtn,uiTayEnable.utBtnState())))
    }

}

@Composable
fun UiTaySwitchCustom(isChecked : Boolean = false,
                      uTHeight : Int = 30,
                      uTWidth : Int = 50,
                      uTPadding : Int = 2,
                      uTBgSelected : Int = R.drawable.uic_tay_ic_bg_round,
                      uTBgUnSelected : Int = R.drawable.uic_tay_ic_bg_round,
                      uTImgSelected : Int = R.drawable.uic_tay_bg_circle,
                      uTImgUnSelected : Int = R.drawable.uic_tay_bg_circle,
                      uTBgFull : Boolean = false,
                      uiTayCheckedChange: (Boolean) -> Unit){
    Row(
        modifier = Modifier
            .height(uTHeight.dp)
            .width(uTWidth.dp)
            .paint(
                painterResource(id = if(isChecked)uTBgSelected else uTBgUnSelected ),
                contentScale = ContentScale.FillBounds) .uiTayNoRippleClickable{
                uiTayCheckedChange.invoke(!isChecked)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement= if(isChecked) Arrangement.End else Arrangement.Start

    ) {
        if (!uTBgFull){
            Image(
                painter = painterResource(if(isChecked)uTImgSelected else uTImgUnSelected ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size((uTHeight - uTPadding).dp).padding(uTPadding.dp)
                    .clip(CircleShape),
                contentDescription = "uiTaySwitchCustom",
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
fun UiTaySwitch(isChecked : Boolean = false,
                uTModel : UiTaySwitchModel = UiTaySwitchModel(),
                uiTayCheckedChange: (Boolean) -> Unit){
    Row(
        modifier = Modifier
            .height(uTModel.uTHeight.dp)
            .width(uTModel.uTWidth.dp)
            .clip(RoundedCornerShape(uTModel.uTRadiusCtn.dp))

            .border(uTModel.uTSizeStrokeCtn.dp,
                colorResource(if(isChecked) uTModel.uTBgStrokeSelectedCtn else uTModel.uTBgStrokeUnSelectedCtn)
                ,
                CircleShape)
            .background(colorResource(if(isChecked) uTModel.uTBgSelectedCtn else uTModel.uTBgUnSelectedCtn)).uiTayNoRippleClickable{
                uiTayCheckedChange.invoke(!isChecked)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement= if(isChecked) Arrangement.End else Arrangement.Start

    ) {
        Box(
            modifier = Modifier
                .size((uTModel.uTHeight - uTModel.uTPadding).dp).padding(uTModel.uTPadding.dp)
                .clip(CircleShape)
                .border(uTModel.uTSizeStroke.dp,
                    colorResource(if(isChecked) uTModel.uTBgStrokeSelected else uTModel.uTBgStrokeUnSelected)
                    , CircleShape)
                .background( colorResource(if(isChecked) uTModel.uTBgSelected else uTModel.uTBgUnSelected))
        )
    }
}

