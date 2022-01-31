package com.example.sunbase_task.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.sunbase_task.api.ApiInterface
import com.example.sunbase_task.api.PagingSource
import javax.inject.Inject
class AppRepository @Inject constructor(private val apiinterface:ApiInterface) {
    fun getResults() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(apiinterface,null) }
        ).liveData
    fun searchResult(query:String)=
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PagingSource(apiinterface,query) }
        ).liveData
}