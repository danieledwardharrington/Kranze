package com.example.kranzeproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kranzeproject.persistence.GroceryDatabase
import com.example.kranzeproject.persistence.GroceryEntity
import com.example.kranzeproject.persistence.GroceryRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GroceryViewModel(application: Application): ViewModel() {
    private val TAG = "GroceryViewModel"
    private var groceryList = MutableLiveData<List<GroceryEntity>>()
    private var groceryDatabase: GroceryDatabase = GroceryDatabase.getInstance(application)!!
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun insert(groceryEntity: GroceryEntity) {
        groceryDatabase.groceryDao().insert(groceryEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {},
            {}
        ).let { compositeDisposable.add(it) }
    }

    fun delete(groceryEntity: GroceryEntity) {
        groceryDatabase.groceryDao().delete(groceryEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {getAllGroceries()},
            { Log.d(TAG, it.toString()) }
        ).let { compositeDisposable.add(it) }
    }

    fun update(groceryEntity: GroceryEntity) {
        //Completable.fromAction { groceryRepository.update(groceryEntity) }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun getAllGroceries() {
        groceryDatabase.groceryDao().getAllGroceries().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                if (!it.isNullOrEmpty()) {
                    groceryList.postValue(it)
                } else {
                    groceryList.postValue(listOf())
                }
                it.forEach {
                    Log.d(TAG, it.toString())
                }
            },
            {}
        ).let { compositeDisposable.add(it) }

    }

    override fun onCleared() {
        compositeDisposable.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getGroceryList(): MutableLiveData<List<GroceryEntity>> {
        return groceryList
    }

}