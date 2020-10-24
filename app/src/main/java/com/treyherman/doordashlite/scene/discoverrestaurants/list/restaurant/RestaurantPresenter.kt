package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import com.treyherman.doordashlite.di.scope.ViewScope
import com.treyherman.doordashlite.scene.discoverrestaurants.DiscoverRestaurantsMvp
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant
import javax.inject.Inject

@ViewScope
class RestaurantPresenter @Inject constructor(
    private val view: RestaurantMvp.View,
    private val parentPresenter: DiscoverRestaurantsMvp.Presenter
) :
    RestaurantMvp.Presenter {

    private lateinit var restaurant: UIRestaurant

    override fun onBind(restaurant: UIRestaurant) {
        this.restaurant = restaurant
        view.displayRestaurant(restaurant)
    }

    override fun onRestaurantClicked() {
        parentPresenter.presentRestaurantDetails(restaurant.id)
    }
}
