package com.treyherman.doordashlite.scene.discoverrestaurants.mapper

import com.treyherman.doordashlite.mocks.response.mockRestaurantResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantsModelMapperTest {

    private val mapper: RestaurantsModelMapper by lazy { RestaurantsModelMapperImpl() }

    private val restaurantResponses by lazy {
        IntRange(1, 3).map {
            mockRestaurantResponse(
                it.toLong(),
                "Name$it",
                "Description$it",
                "img$it.png",
                "Status$it"
            )
        }
    }

    @Test
    fun mapRestaurants_result() {
        val uiRestaurants = mapper.mapRestaurants(restaurantResponses)
        assertEquals(uiRestaurants.size, restaurantResponses.size)

        val restaurant1 = uiRestaurants[0]
        assertEquals(restaurant1.id, 1L)
        assertEquals(restaurant1.name, "Name1")
        assertEquals(restaurant1.foodDescription, "Description1")
        assertEquals(restaurant1.imageUrl, "img1.png")
        assertEquals(restaurant1.status, "Status1")

        val restaurant2 = uiRestaurants[1]
        assertEquals(restaurant2.id, 2L)
        assertEquals(restaurant2.name, "Name2")
        assertEquals(restaurant2.foodDescription, "Description2")
        assertEquals(restaurant2.imageUrl, "img2.png")
        assertEquals(restaurant2.status, "Status2")

        val restaurant3 = uiRestaurants[2]
        assertEquals(restaurant3.id, 3L)
        assertEquals(restaurant3.name, "Name3")
        assertEquals(restaurant3.foodDescription, "Description3")
        assertEquals(restaurant3.imageUrl, "img3.png")
        assertEquals(restaurant3.status, "Status3")

    }
}