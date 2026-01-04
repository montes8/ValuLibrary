package com.valu.valulibrary

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

class MainActivity : ComponentActivity() {

    private val updateOptions = AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build()

    companion object {
        private const val UPDATE_CODE = 10001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
            PlayValuTheme {
                Scaffold(topBar = {
                    if(viewModel.visibleToolbar){
                        UiTayCToolBar(uiTayText = stringResource(R.string.tb_title_home), uiTayModifier = UiTayToolBarModel(
                            uTTypeEnd = true
                        )) {
                            MediaPlayerSingleton.positionMusic =  viewModel.uiStatePosition
                            MediaPlayerSingleton.positionDurationMusic = MediaPlayerSingleton.playCurrentPosition()
                            PermissionManager.checkOverlayPermission(this) {
                                startService(Intent(this, MusicService::class.java))
                                MediaPlayerSingleton.playStop()
                                finish()
                            }
                        }
                    }
                }, content = { paddingValues ->
                    Navigation(viewModel,paddingValues)
                })

            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}