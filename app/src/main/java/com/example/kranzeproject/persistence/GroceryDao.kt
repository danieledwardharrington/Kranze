package com.example.kranzeproject.persistence

import android.database.Observable
import androidx.room.*

@Dao
interface GroceryDao {

    @Insert
    fun insert(groceryItem: GroceryEntity)

    @Update
    fun update(groceryItem: GroceryEntity)

    @Delete
    fun delete(groceryItem: GroceryEntity)

    @Query("DELETE FROM grocery_table")
    fun deleteAllGroceries()

    @Query("SELECT * FROM grocery_table ORDER BY id DESC")
    fun getAllGroceries(): Observable<List<GroceryEntity>>
}