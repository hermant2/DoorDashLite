package com.treyherman.doordashlite.scene.discoverrestaurants.mapper

import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant
import com.treyherman.network.rest.model.response.RestaurantResponse

interface RestaurantsModelMapper {
    fun mapRestaurants(restaurantResponses: List<RestaurantResponse>): List<UIRestaurant>
}