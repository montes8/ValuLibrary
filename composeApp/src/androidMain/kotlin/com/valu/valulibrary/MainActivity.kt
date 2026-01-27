package com.valu.valulibrary

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.valu.valulibrary.ui.AppViewModel
import com.valu.valulibrary.ui.splash.ScreenMySplash
import com.valu.valulibrary.utils.ValeTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val updateOptions = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()
    private val viewModel: AppViewModel by viewModel()
    companion object {
        private const val UPDATE_CODE = 10001
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContent {
            validateVersionUpdate()
        }
    }

    private fun validateVersionUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(
                    AppUpdateType.IMMEDIATE
                )
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    this,
                    updateOptions,
                    UPDATE_CODE
                )
                finish()
            } else {
                configInit()
            }
        }
        appUpdateInfoTask.addOnFailureListener {
            configInit()
        }
    }

    private fun configInit(){
        setContent {
            ValeTheme {
                ScreenMySplash(viewModel)
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}