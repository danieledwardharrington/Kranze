package com.example.kranzeproject.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_table")
class GroceryEntity(@ColumnInfo(name = "grocery_name")
                    private var name: String = "",
                    @ColumnInfo(name = "grocery_count")
                    private var count: Int = 0) {

    @PrimaryKey(autoGenerate = true)
    private var id: Long = 0

    fun setId(newId: Long) {
        id = newId
    }

    fun getiD(): Long {
        return id
    }

    fun setName(newName: String) {
        name = newName
    }

    fun getName(): String {
        return name
    }

    fun setCount(newCount: Int) {
        count = newCount
    }

    fun getCount(): Int {
        return count
    }
    
}