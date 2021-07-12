package com.app.ar.launcher.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.appinfo.MobileAppInfo
import com.app.appinfo.model.AppInfo
import com.app.ar.launcher.R
import com.app.ar.launcher.databinding.ItemRowViewListBinding
import javax.inject.Inject

class AppsDrawerAdapter @Inject constructor(mobileAppInfo: MobileAppInfo) :
    RecyclerView.Adapter<AppsDrawerAdapter.ViewHolder>() {

    private var appsList: List<AppInfo>? = null

    fun setUpApps(mobileAppInfo: MobileAppInfo) {
        appsList = mobileAppInfo.fetchAppList()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //This is what adds the code we've written in here to our target view
        val inflater = LayoutInflater.from(parent.context)

        // View view =DataBindingUtil.inflater< ItemRowViewListBinding >(R.layout.item_row_view_list, parent, false);
        //LayoutInflater inflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        val rowViewListBinding: ItemRowViewListBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_row_view_list, parent, false)
        return ViewHolder(rowViewListBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(appsList!![position])
    }

    override fun getItemCount(): Int {
        return appsList!!.size
    }


    inner class ViewHolder(var itemBinding: ItemRowViewListBinding) : RecyclerView.ViewHolder(
        itemBinding.root
    ) {
        fun bind(modelType: AppInfo?) {
            itemBinding.model = modelType
        }
    }

    init {

        //This is where we build our list of app details, using the app
        //object we created to store the label, package name and icon
        setUpApps(mobileAppInfo)
    }
}