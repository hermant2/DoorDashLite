package com.treyherman.doordashlite.scene.discoverrestaurants

import androidx.recyclerview.widget.DiffUtil
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant


interface DiscoverRestaurantsMvp {
    interface View {
        fun displayRestaurants(restaurants: List<UIRestaurant>)

        fun updateRestaurants(restaurants: List<UIRestaurant>, diffResult: DiffUtil.DiffResult)

        fun displayLoading()

        fun hideLoading()

        fun displayEmptyContent(message: String)

        fun openRestaurantDetailScene(restaurantId: Long)
    }

    interface Presenter {
        fun onCreate()

        fun onDestroy()

        fun onRefresh(currentRestaurants: List<UIRestaurant>)

        fun onTryAgainClicked(currentRestaurants: List<UIRestaurant>)

        fun presentRestaurantDetails(restaurantId: Long)
    }
}
