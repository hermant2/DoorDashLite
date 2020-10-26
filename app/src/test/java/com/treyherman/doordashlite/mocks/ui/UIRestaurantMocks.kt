package com.treyherman.doordashlite.mocks.ui

import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant

fun mockUIRestaurant(
    id: Long = 1L,
    name: String = "Name",
    imageUrl: String = "img.png",
    foodDescription: String = "Description",
    status: String = "Status"
): UIRestaurant = UIRestaurant(id, name, imageUrl, foodDescription, status)
