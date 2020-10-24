package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant

interface RestaurantMvp {
    interface View {
        fun displayRestaurant(restaurant: UIRestaurant)
    }

    interface Presenter {
        fun onBind(restaurant: UIRestaurant)

        fun onRestaurantClicked()
    }
}
