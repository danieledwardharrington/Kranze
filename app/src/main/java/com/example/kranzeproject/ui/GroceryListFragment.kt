package com.example.kranzeproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kranzeproject.R
import com.example.kranzeproject.adapter.GroceriesAdapter
import com.example.kranzeproject.persistence.GroceryEntity
import com.example.kranzeproject.viewmodel.GroceryViewModel
import com.example.kranzeproject.viewmodel.GroceryViewModelFactory
import kotlinx.android.synthetic.main.fragment_grocery_list.*

class GroceryListFragment: Fragment(), GroceriesAdapter.DeleteListener {
    private val TAG: String = "GroceryListFragment"

    private lateinit var groceryViewModel: GroceryViewModel
    private var groceriesAdapter: GroceriesAdapter = GroceriesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grocery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRV()
    }

    private fun initRV() {
        initViewModel()
        groceries_rv.layoutManager = LinearLayoutManager(context)
        groceries_rv.setHasFixedSize(true)
        groceries_rv.adapter = groceriesAdapter
        groceriesAdapter.setDeleteListener(this)
    }

    private fun initViewModel() {
        groceryViewModel = ViewModelProvider(this, GroceryViewModelFactory(requireActivity().application)).get(GroceryViewModel::class.java)
        groceryViewModel.getAllGroceries()
        groceryViewModel.getGroceryList().observe(viewLifecycleOwner, Observer { groceriesAdapter.submitList(ArrayList(it)) })
    }

    override fun onDeleteClicked(groceryEntity: GroceryEntity) {
        groceryViewModel.delete(groceryEntity)
    }

}