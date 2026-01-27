package com.valu.valulibrary.utils

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

fun  String.htmlDriveMovie():String{
    return """
        <html>
            <body style="margin:0;padding:0;background-color:black;">
                <iframe 
                    src="$this" 
                    width="100%" 
                    height="100%" 
                    frameborder="0" 
                    allow="autoplay; encrypted-media" 
                    allowfullscreen>
                </iframe>
            </body>
        </html>
    """
}

fun Context.versionApp(): String {
    return try {
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            this.packageManager.getPackageInfo(this.packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            @Suppress("DEPRECATION")
            this.packageManager.getPackageInfo(this.packageName, 0)
        }
        packageInfo.versionName ?: defaultMessage
    } catch (e: Exception) {
        e.printStackTrace()
        defaultMessage
    }
}