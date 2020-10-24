package com.treyherman.doordashlite.scene.discoverrestaurants.mapper

import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant
import com.treyherman.network.rest.model.response.RestaurantResponse

class RestaurantsModelMapperImpl : RestaurantsModelMapper {
    override fun mapRestaurants(restaurantResponses: List<RestaurantResponse>): List<UIRestaurant> {
        return restaurantResponses.map {
            UIRestaurant(
                it.id,
                it.name,
                it.coverImgUrl,
                it.description,
                it.status
            )
        }
    }
}