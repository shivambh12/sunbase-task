package com.example.sunbase_task.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunbase_task.R
import com.example.sunbase_task.adapter.AppAdapter
import com.example.sunbase_task.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {
    lateinit var madapter: AppAdapter
    private val viewModel by viewModels<AppViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.visibility =View.VISIBLE
        if(checkForInternet()) {
            madapter = AppAdapter()
            recycle.layoutManager = GridLayoutManager(activity, 2)
            recycle.hasFixedSize()
            recycle.adapter = madapter
            viewModel.photos.observe(viewLifecycleOwner, Observer {
                madapter.submitData(viewLifecycleOwner.lifecycle, it)
                progressBar.visibility = View.GONE
            })
        }
        else
        {
            progressBar.visibility = View.GONE
            Toast.makeText(activity,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkForInternet(): Boolean {

        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }

    }

    override fun onStart() {

        super.onStart()
        progressBar.visibility =View.VISIBLE
        if(checkForInternet()) {

            madapter = AppAdapter()
            recycle.layoutManager = GridLayoutManager(activity, 2)
            recycle.hasFixedSize()
            recycle.adapter = madapter
            viewModel.photos.observe(viewLifecycleOwner, Observer {
                madapter.submitData(viewLifecycleOwner.lifecycle, it)
                progressBar.visibility = View.GONE
            })
        }
        else
        {
            progressBar.visibility = View.GONE
            Toast.makeText(activity,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }
}