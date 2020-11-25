package com.example.kranzeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kranzeproject.ui.GroceryListFragment

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initComponents(savedInstanceState: Bundle?) {
        supportActionBar!!.title = "Grocery List"
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.main_activity_frag_container, GroceryListFragment()).commit()
        }
    }
}