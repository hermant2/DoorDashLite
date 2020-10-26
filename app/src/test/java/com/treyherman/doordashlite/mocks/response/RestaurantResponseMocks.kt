package com.treyherman.doordashlite.mocks.response

import com.treyherman.network.rest.model.response.RestaurantResponse

fun mockRestaurantResponse(
    id: Long = 1L,
    name: String = "Name",
    description: String = "Description",
    coverImgUrl: String = "img.png",
    status: String = "Status",
    deliveryFee: Int = 100
): RestaurantResponse = RestaurantResponse(id, name, description, coverImgUrl, status, deliveryFee)
