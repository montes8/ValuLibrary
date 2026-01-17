package com.gb.vale.uitaylibraryutils

import android.os.Bundle
import android.os.Handler

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.gb.vale.uitaylibrarycompose.button.UiTayCButton
import com.gb.vale.uitaylibrarycompose.button.UiTaySwitch
import com.gb.vale.uitaylibrarycompose.button.UiTaySwitchCustom
import com.gb.vale.uitaylibraryutils.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ScreemTest()

            }
        }
    }
}

@Composable
fun ScreemTest(){
    var enable by remember { mutableStateOf(true) }
    Column {
        UiTaySwitchCustom (isChecked =enable , uTBgFull = true){
            enable = it
        }
    }
}