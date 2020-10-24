package com.treyherman.network.rest.service

import com.treyherman.network.rest.model.response.RestaurantResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/restaurant")
    fun restaurantsByLatLngOnce(
        @Query("lat") lat: Float,
        @Query("lng") lng: Float
    ): Single<List<RestaurantResponse>>

    @GET("v2/restaurant/{id}")
    fun restaurantDetailOnce(@Path("id") id: Long): Single<RestaurantResponse>
}
