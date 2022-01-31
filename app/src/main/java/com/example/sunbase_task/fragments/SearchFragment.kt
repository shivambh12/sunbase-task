package com.example.sunbase_task.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sunbase_task.R
import com.example.sunbase_task.adapter.AppAdapter
import com.example.sunbase_task.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.search_fragment.*
@AndroidEntryPoint
class SearchFragment: Fragment(R.layout.search_fragment) {
    lateinit var madapter: AppAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(checkForInternet()) {
            val viewModel by viewModels<AppViewModel>()
            madapter = AppAdapter()
            rvSearchNews.layoutManager = GridLayoutManager(activity, 2)
            rvSearchNews.hasFixedSize()
            rvSearchNews.adapter = madapter
            search_photo.setOnClickListener {
                if (etSearch.text.toString() != null && etSearch.text.toString().isNotEmpty()) {
                    viewModel.searchphoto(etSearch.text.toString())
                    viewModel.searchdata.observe(viewLifecycleOwner, Observer {
                        madapter.submitData(viewLifecycleOwner.lifecycle, it)
                    })
                }
            }
        }
        else
        {

            Toast.makeText(activity,"No Internet Connection", Toast.LENGTH_SHORT).show()
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
}