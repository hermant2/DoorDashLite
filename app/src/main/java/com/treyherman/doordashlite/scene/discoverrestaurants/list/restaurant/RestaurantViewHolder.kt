package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import com.treyherman.commonui.list.AbsViewHolder
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant

class RestaurantViewHolder(
    private val view: RestaurantView
) : AbsViewHolder<UIRestaurant>(view) {
    override fun onBind(data: UIRestaurant) {
        view.bind(data)
    }
}