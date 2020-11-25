package com.example.kranzeproject.persistence

import android.database.Observable
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(groceryItem: GroceryEntity): Completable

    @Update
    fun update(groceryItem: GroceryEntity): Completable

    @Delete
    fun delete(groceryItem: GroceryEntity): Completable

    @Query("DELETE FROM grocery_table")
    fun deleteAllGroceries()

    @Query("SELECT * FROM grocery_table ORDER BY id ASC")
    fun getAllGroceries(): Single<List<GroceryEntity>>

}