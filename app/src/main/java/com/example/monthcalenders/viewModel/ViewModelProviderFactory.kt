package com.example.monthcalenders.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.monthcalenders.App
import com.example.monthcalenders.repo.Repo

class ViewModelProviderFactory (
    val app: App,
    val newsRepository: Repo
        ): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel(newsRepository,app) as T
    }
}
