package com.example.kranzeproject.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GroceryViewModelFactory: ViewModelProvider.Factory {

    private lateinit var mApplication: Application

    constructor(application: Application) {
        mApplication = application
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GroceryViewModel(mApplication) as T
    }
}