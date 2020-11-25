package com.example.kranzeproject.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

class GroceryListFragment: Fragment(), GroceriesAdapter.DeleteListener, AddGroceryDialogFragment.SaveGroceryListener {
    private val TAG: String = "GroceryListFragment"

    private lateinit var groceryViewModel: GroceryViewModel
    private var groceriesAdapter: GroceriesAdapter = GroceriesAdapter()
    private var groceryArrayList = ArrayList<GroceryEntity>()
    private val addGroceryDialogFragment = AddGroceryDialogFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grocery_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        addGroceryDialogFragment.setSaveListener(this)
        initRV()
        add_grocery_item_fab.setOnClickListener {
            addGroceryDialogFragment.show(childFragmentManager, "AddGroceryDialogFragment")
        }
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
        groceryViewModel.getGroceryList().observe(viewLifecycleOwner, Observer {
            groceryArrayList = ArrayList(it)
            groceriesAdapter.submitList(groceryArrayList)
        })
    }

    override fun onDeleteClicked(groceryEntity: GroceryEntity) {
        groceryViewModel.delete(groceryEntity)
    }

    override fun onSaveGrocery(groceryEntity: GroceryEntity) {
        Log.d(TAG, "onSaveGrocery")

        var groceryExists = false
        groceryArrayList.forEach {
            if(groceryEntity.getName().equals(it.getName(), ignoreCase = true)) {
                groceryExists = true
                it.setCount(groceryEntity.getCount())
                groceryViewModel.update(it)
            }
        }

        if(!groceryExists) {
            groceryViewModel.insert(groceryEntity)
            groceryArrayList.add(groceryEntity)
        }

        groceriesAdapter.submitList(groceryArrayList)

        addGroceryDialogFragment.dismiss()

    }

}