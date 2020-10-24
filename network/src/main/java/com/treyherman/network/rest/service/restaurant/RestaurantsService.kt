package com.treyherman.network.rest.service.restaurant

import com.treyherman.network.rest.model.response.RestaurantResponse
import io.reactivex.Single

interface RestaurantsService {
    fun restaurantsByLatLngOnce(lat: Float, lng: Float): Single<List<RestaurantResponse>>

    fun restaurantDetailOnce(id: Long): Single<RestaurantResponse>
}
