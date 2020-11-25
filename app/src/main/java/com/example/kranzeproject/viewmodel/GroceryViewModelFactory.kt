package com.example.kranzeproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private var mApplication: Application = application

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return GroceryViewModel(mApplication) as T
    }
}