package com.treyherman.network.rest.service.restaurant

import com.treyherman.network.rest.model.response.RestaurantResponse
import com.treyherman.network.rest.service.ApiService
import com.treyherman.utilities.extensions.subscribeOnIO
import io.reactivex.Single
import javax.inject.Inject

class RestaurantsServiceImpl @Inject constructor(
    private val apiService: ApiService
) : RestaurantsService {
    override fun restaurantsByLatLngOnce(lat: Float, lng: Float): Single<List<RestaurantResponse>> {
        return apiService.restaurantsByLatLngOnce(lat, lng).subscribeOnIO()
    }

    override fun restaurantDetailOnce(id: Long): Single<RestaurantResponse> {
        return apiService.restaurantDetailOnce(id).subscribeOnIO()
    }
}
