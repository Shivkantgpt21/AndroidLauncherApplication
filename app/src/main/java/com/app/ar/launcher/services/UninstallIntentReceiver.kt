package com.app.ar.launcher.services

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.app.ar.launcher.R

class UninstallIntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // fetching package names from extras
        val packageNames = intent.getStringArrayExtra("android.intent.extra.PACKAGES")
        if (packageNames != null) {
            for (packageName in packageNames) {
                if (packageName != null && packageName == "YOUR_APPLICATION_PACKAGE_NAME") {
                    // User has selected our application under the Manage Apps settings

                    val mBuilder = NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_apps)
                        .setContentTitle("INSTALL/UNINSTALL")
                        .setContentText("INSTALL/UNINSTALL")
                    // .setContentIntent(pendingIntent);
                    val notificationManager =
                        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.notify(1, mBuilder.build())
                    println("service called")
                }
            }
        }
    }
}