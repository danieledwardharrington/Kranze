package com.example.kranzeproject.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kranzeproject.persistence.GroceryEntity
import kotlinx.android.synthetic.main.card_view_grocery_item.view.*

class GroceriesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var groceriesList: ArrayList<GroceryEntity> = ArrayList()
    private lateinit var deleteListener: DeleteListener

    interface DeleteListener {
        fun onDeleteClicked(groceryEntity: GroceryEntity)
    }

    fun setDeleteListener(listener: DeleteListener) {
        deleteListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GroceriesAdapter.GroceryViewHolder -> {
                holder.bind(groceriesList[position])
                holder.deleteGroceryIV.setOnClickListener {
                    deleteListener.onDeleteClicked(groceriesList[position])
                    removeAt(holder.adapterPosition)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return groceriesList.size
    }

    fun submitList(newList: List<GroceryEntity>) {
        groceriesList = newList as ArrayList<GroceryEntity>
        notifyDataSetChanged()
    }

    class GroceryViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        private val groceryNameTV = itemView.grocery_name_tv
        private val groceryCountTV = itemView.grocery_count_tv
        private val grocerIdTV = itemView.grocery_id_tv
        val deleteGroceryIV = itemView.delete_iv

        fun bind(groceryEntity: GroceryEntity) {
            groceryNameTV.text = groceryEntity.getName()
            groceryCountTV.text = groceryEntity.getCount().toString()
            grocerIdTV.text = groceryEntity.getId().toString()
        }

    }//viewholder

    private fun removeAt(position: Int) {
        groceriesList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, groceriesList.size)
    }

    private fun add(groceryEntity: GroceryEntity) {
        groceriesList.add(groceryEntity)
        notifyDataSetChanged()
    }
}