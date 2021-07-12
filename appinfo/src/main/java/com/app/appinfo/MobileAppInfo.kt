package com.app.appinfo

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import com.app.appinfo.model.AppInfo
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MobileAppInfo @Inject constructor(@param:ApplicationContext  val context: Context) {

    private var fetchAppList: MutableList<AppInfo>? = null

    fun fetchAppList(): List<AppInfo> {
        val packageManager = context.packageManager
        val activities = getActivities(packageManager)
        if (fetchAppList?.size?:0 != activities.size-1 ) {
            fetchAppList = ArrayList()
            try {
                // ExecutorService executorService = Executors.newCachedThreadPool();
                for (resolver in activities) {
                    val appName = resolver.loadLabel(packageManager) as String
                    if (appName != applicationName) {
                        val appInfo = AppInfo()
                        appInfo.appName = appName
                        appInfo.launcherActivityClassName = resolver.activityInfo.name
                        appInfo.packageName = resolver.activityInfo.packageName
                        appInfo.icon = resolver.activityInfo.loadIcon(packageManager)
                        val pi = packageManager.getPackageInfo(resolver.activityInfo.packageName, 0)
                        appInfo.versionCode = pi.versionCode.toString() + ""
                        appInfo.versionName = pi.versionName + ""
                        fetchAppList!!.add(appInfo)
                    }
                }
                //executorService.shutdown();
                //executorService.awaitTermination(3, TimeUnit.SECONDS);
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return fetchAppList!!
    }

    private val applicationName: String
        private get() {
            val applicationInfo = context.applicationInfo
            val stringId = applicationInfo.labelRes
            return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(
                stringId
            )
        }

    private fun getActivities(packageManager: PackageManager): List<ResolveInfo> {
        val intent = Intent(Intent.ACTION_MAIN, null).addCategory(Intent.CATEGORY_LAUNCHER)
        val activities = packageManager.queryIntentActivities(intent, 0)
        Collections.sort(activities, ResolveInfo.DisplayNameComparator(packageManager))
        return activities
    }
}