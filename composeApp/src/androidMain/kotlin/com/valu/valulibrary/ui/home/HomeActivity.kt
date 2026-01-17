package com.valu.valulibrary.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Scaffold
import androidx.compose.ui.res.stringResource
import com.gb.vale.uitaylibrarycompose.extra.UiTayCToolBar
import com.gb.vale.uitaylibrarycompose.model.UiTayToolBarModel
import com.valu.valulibrary.R
import com.valu.valulibrary.ui.nav.ValeNavigation
import com.valu.valulibrary.utils.ValeTheme

class HomeActivity : AppCompatActivity() {

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValeTheme {
                ScreenHome()
            }
        }
    }
}