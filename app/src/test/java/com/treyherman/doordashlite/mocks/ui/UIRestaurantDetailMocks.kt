package com.treyherman.doordashlite.mocks.ui

import com.treyherman.doordashlite.scene.restaurantdetail.model.UIRestaurantDetail

fun mockUIRestaurantDetail(
    name: String = "Name",
    description: String = "Description",
    imageUrl: String = "img.png",
    status: String = "Status",
    formattedDeliveryFee: String = "$1.00"
): UIRestaurantDetail = UIRestaurantDetail(name, description, imageUrl, status, formattedDeliveryFee)
