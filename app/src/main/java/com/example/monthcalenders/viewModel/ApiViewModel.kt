package com.example.monthcalenders.viewModel

import androidx.lifecycle.*
import com.example.monthcalenders.App
import com.example.monthcalenders.models.UsersItem
import com.example.monthcalenders.repo.Repo
import com.example.monthcalenders.utils.LoadingState

class ApiViewModel(val repo: Repo,app: App): AndroidViewModel(app) {
    val _registerLiveData = MutableLiveData<List<UsersItem>>()
    val registerLiveData : LiveData<List<UsersItem>>
        get() = _registerLiveData

    val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState

    fun safeSearchNewsCall() {
        _loadingState.value = LoadingState.LOADING
        repo.getApiCall({
            _registerLiveData.postValue(it)
            _loadingState.value = LoadingState.LOADED
        },{
            _loadingState.value = LoadingState.error(it)
        })
    }
}