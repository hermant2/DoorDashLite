package com.treyherman.doordashlite.scene.discoverrestaurants.diff

import androidx.recyclerview.widget.DiffUtil
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant

class RestaurantsDiffCallback(
    private val oldItems: List<UIRestaurant>,
    private val newItems: List<UIRestaurant>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
