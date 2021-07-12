package com.app.ar.launcher.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.ar.launcher.R
import com.app.ar.launcher.databinding.FragmentAppsDrawerBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppsDrawerFragment : Fragment() {
    @Inject
    lateinit var adapter: AppsDrawerAdapter
    var layoutManager: RecyclerView.LayoutManager? = null
    var dataBinding: FragmentAppsDrawerBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_apps_drawer, container, false
        )
        return dataBinding?.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // adapter = new AppsDrawerAdapter(getContext());
        layoutManager = LinearLayoutManager(context)
        dataBinding!!.appDrawerRecylerView.layoutManager = layoutManager
        dataBinding!!.appDrawerRecylerView.adapter = adapter
    }
}