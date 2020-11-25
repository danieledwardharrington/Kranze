package com.example.kranzeproject.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.kranzeproject.R
import com.example.kranzeproject.persistence.GroceryEntity
import kotlinx.android.synthetic.main.dialog_fragment_add_grocery_item.*

class AddGroceryDialogFragment: DialogFragment() {
    private val TAG: String = "AddGroceryFragment"
    private lateinit var saveListener: SaveGroceryListener

    interface SaveGroceryListener {
        fun onSaveGrocery(groceryEntity: GroceryEntity)
    }

    fun setSaveListener(listener: SaveGroceryListener) {
        saveListener = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_add_grocery_item, null)
        val groceryNameET = view.findViewById<EditText>(R.id.grocery_name_edit_text)
        val groceryCountET = view.findViewById<EditText>(R.id.grocery_count_edit_text)
        builder.setView(view).setTitle("Add Grocery").setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dismiss() })
            .setPositiveButton("Save", DialogInterface.OnClickListener { dialog, which ->
                if (groceryNameET.text.toString().trim().isNotEmpty() && groceryCountET.text.toString().trim().isNotEmpty()) {
                    if (groceryCountET.text.toString().trim().toInt() > 0) {
                        val groceryEntity = GroceryEntity(groceryNameET.text.toString().trim(), groceryCountET.text.toString().trim().toInt())
                        saveListener.onSaveGrocery(groceryEntity)
                    } else {
                        showLongToast("Grocery count must be greater than 0")
                    }
                } else {
                    showLongToast("Grocery name and count must not be empty")
                }
            })

        return builder.create()
    }


    private fun showLongToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}