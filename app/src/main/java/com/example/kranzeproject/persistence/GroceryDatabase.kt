package com.example.kranzeproject.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryEntity::class], version = 1)
abstract class GroceryDatabase: RoomDatabase() {

        abstract fun groceryDao(): GroceryDao

        companion object {
            private var instance: GroceryDatabase? = null

            fun getInstance(context: Context): GroceryDatabase? {
                if (instance == null) {
                    synchronized(GroceryDatabase::class){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            GroceryDatabase::class.java,
                            "repo_db.db"
                        ).fallbackToDestructiveMigration().build()
                    }
                }

                return instance
            }
        }

}