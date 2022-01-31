package com.example.sunbase_task.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.sunbase_task.repository.AppRepository
class AppViewModel @ViewModelInject constructor(private val repository: AppRepository)
    : ViewModel() {
    val photos = repository.getResults().cachedIn(viewModelScope)
    var searchdata=repository.getResults().cachedIn(viewModelScope)
    fun searchphoto(query: String)
    {
        searchdata = repository.searchResult(query).cachedIn(viewModelScope)
    }
}