package com.gb.vale.uitaylibrarycompose.model

import com.gb.vale.uitaylibrarycompose.R

class UiTayButtonModel (
    var uTHeight : Int = 48,
    var uTBgColor : Int = R.color.ui_tay_black,
    var uTStrokeColor : Int = R.color.ui_tay_black,
    var uTBgDisableColor : Int = R.color.ui_tay_gray,
    var uTStrokeDisableColor : Int = R.color.ui_tay_gray,
    var uTBgSelectedColor : Int = R.color.ui_tay_black,
    var uTStrokeSelectedColor : Int = R.color.ui_tay_black,
    var uTBgSecondaryColor : Int = R.color.ui_tay_white,
    var uTStrokeSecondaryColor : Int = R.color.ui_tay_black,
    var uTBgSecondaryDisableColor : Int = R.color.ui_tay_gray_secondary,
    var uTStrokeSecondaryDisableColor : Int = R.color.ui_tay_gray,
    var uTBgSecondarySelectedColor : Int = R.color.ui_tay_black,
    var uTStrokeSecondarySelectedColor : Int = R.color.ui_tay_white,
    var uTTextColor: Int = R.color.ui_tay_white,
    var uTTextColorDisable: Int = R.color.ui_tay_white,
    var uTTextColorSelected: Int = R.color.ui_tay_white,
    var uTTextColorSecondary: Int = R.color.ui_tay_black,
    var uTTextColorDisableSecondary: Int = R.color.ui_tay_gray,
    var uTTextColorSelectedSecondary: Int = R.color.ui_tay_black,
    var uTTextFont : Int = R.font.ui_tay_font_button,
    var uTColorIconDefault: Boolean = false,
    var uTIconStart : Int? = null,
    var uTIconEnd : Int? = null,
    var uTTextSize : Int = 20,
    var uTStrokeWith : Int = 1,
    var uTRadius : Int = 62)

fun Boolean.utBtnState(selected : Boolean = false)=  if (selected) UTStateCButton.UI_BTN_SELECTED else
        if (this)UTStateCButton.UT_BTN_ENABLE else UTStateCButton.UI_BTN_DISABLE

fun UiTayButtonModel.uiTayStroke(type :UTStyleCButton,state : UTStateCButton) =
    if(type.uiCBtnPrimary())uiTayMStroke(state) else uiTayMStrokeS(state)

fun UiTayButtonModel.uiTayBackground(type :UTStyleCButton,state : UTStateCButton) =
    if(type.uiCBtnPrimary())uiTayMBackground(state) else uiTayMBackgroundS(state)


fun UiTayButtonModel.uiTayTextColor(type :UTStyleCButton,state : UTStateCButton) =
    if(type.uiCBtnPrimary())uiTayMText(state) else uiTayMTextS(state)


fun UiTayButtonModel.uiTayMStroke(state :UTStateCButton ): Int{
    return when(state){
        UTStateCButton.UT_BTN_ENABLE ->{uTStrokeColor}
       UTStateCButton.UI_BTN_DISABLE ->{uTStrokeDisableColor}
      UTStateCButton.UI_BTN_SELECTED ->{uTStrokeSelectedColor}
    }
}

fun UiTayButtonModel.uiTayMBackground(state :UTStateCButton ): Int{
    return when(state){
        UTStateCButton.UT_BTN_ENABLE ->{uTBgColor}
        UTStateCButton.UI_BTN_DISABLE ->{uTBgDisableColor}
        UTStateCButton.UI_BTN_SELECTED ->{uTBgSelectedColor}
    }
}

fun UiTayButtonModel.uiTayMStrokeS(state :UTStateCButton ): Int{
    return when(state){
        UTStateCButton.UT_BTN_ENABLE ->{uTStrokeSecondaryColor}
        UTStateCButton.UI_BTN_DISABLE ->{uTStrokeSecondaryDisableColor}
        UTStateCButton.UI_BTN_SELECTED ->{uTStrokeSecondarySelectedColor}
    }
}

fun UiTayButtonModel.uiTayMBackgroundS(state :UTStateCButton ): Int{
    return when(state){
        UTStateCButton.UT_BTN_ENABLE ->{uTBgSecondaryColor}
        UTStateCButton.UI_BTN_DISABLE ->{uTBgSecondaryDisableColor}
        UTStateCButton.UI_BTN_SELECTED ->{uTBgSecondarySelectedColor}
    }
}

fun UiTayButtonModel.uiTayMTextS(state :UTStateCButton ): Int{
    return when(state){
        UTStateCButton.UT_BTN_ENABLE ->{uTTextColorSecondary}
        UTStateCButton.UI_BTN_DISABLE ->{uTTextColorDisableSecondary}
        UTStateCButton.UI_BTN_SELECTED ->{uTTextColorSelectedSecondary}
    }
}

fun UiTayButtonModel.uiTayMText(state :UTStateCButton ): Int{
    return when(state){
        UTStateCButton.UT_BTN_ENABLE ->{uTTextColor}
        UTStateCButton.UI_BTN_DISABLE ->{uTTextColorDisable}
        UTStateCButton.UI_BTN_SELECTED ->{uTTextColorSelected}
    }
}

fun UTStyleCButton.uiCBtnPrimary()= this == UTStyleCButton.UI_TAY_PRIMARY

enum class UTStateCButton(var code: Int) {
    UT_BTN_ENABLE(0),
    UI_BTN_DISABLE(1),
    UI_BTN_SELECTED(2)
}

enum class UTStyleCButton(var code: Int) {
    UI_TAY_PRIMARY(0),
    UI_TAY_SECONDARY(1)
}