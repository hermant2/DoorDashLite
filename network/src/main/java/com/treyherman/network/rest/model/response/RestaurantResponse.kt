package com.treyherman.network.rest.model.response

data class RestaurantResponse(
    val id: Long,
    val name: String,
    val description: String,
    val coverImgUrl: String,
    val status: String,
    val deliveryFee: Int
)
