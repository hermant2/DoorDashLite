package com.treyherman.doordashlite.scene.restaurantdetail.mapper

import com.treyherman.doordashlite.scene.restaurantdetail.model.UIRestaurantDetail
import com.treyherman.network.rest.model.response.RestaurantResponse

interface RestaurantDetailModelMapper {
    fun mapRestaurantDetail(response: RestaurantResponse): UIRestaurantDetail
}