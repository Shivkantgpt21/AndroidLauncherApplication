package com.app.ar.launcher.ui.activity

import android.content.Context
import com.app.appinfo.MobileAppInfo
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class Application : android.app.Application() {

    @Inject
    lateinit var mobileAppInfo: MobileAppInfo

    override fun onCreate() {
        super.onCreate()
        mobileAppInfo.fetchAppList()
        instance = this
    }

    companion object {
        var instance: Application? = null
            private set

        val context: Context?
            get() = instance
    }
}