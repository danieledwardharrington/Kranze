package com.example.kranzeproject.persistence

import android.app.Application
import android.database.Observable
import android.util.Log

class GroceryRepository(application: Application) {
    private val TAG = "GroceryRepository"

    private val database: GroceryDatabase = GroceryDatabase.getInstance(application)!!
    private var groceryDao: GroceryDao = database.groceryDao()

    fun insert(groceryEntity: GroceryEntity) {
        Log.d(TAG, "INSERT")
        Log.d(TAG, groceryEntity.toString())
        groceryDao.insert(groceryEntity)
    }

    fun delete(groceryEntity: GroceryEntity) {
        Log.d(TAG, "DELETE")
        groceryDao.delete(groceryEntity)
    }

    fun update(groceryEntity: GroceryEntity) {
        Log.d(TAG, "UPDATE")
        groceryDao.update(groceryEntity)
    }

    fun getAllGroceries(): Observable<List<GroceryEntity>> {
        Log.d(TAG, "GetAll")
        return groceryDao.getAllGroceries()
    }

}