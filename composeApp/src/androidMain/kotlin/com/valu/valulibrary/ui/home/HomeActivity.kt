package com.valu.valulibrary.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.nav.ValeNavigationMain
import com.valu.valulibrary.utils.ValeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModel()
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
        viewModel.updateProduct()
        setContent {
            ValeTheme {
                  ValeNavigationMain()
            }
        }
    }
}