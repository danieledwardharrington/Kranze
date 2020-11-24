package com.example.kranzeproject.viewmodel

import android.app.Application
import com.example.kranzeproject.persistence.GroceryRepository
import io.reactivex.disposables.CompositeDisposable

class GroceryViewModel(application: Application) {

    private var groceryRepo: GroceryRepository = GroceryRepository(application)
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

}