package com.treyherman.doordashlite.scene.discoverrestaurants.diff

import com.treyherman.doordashlite.mocks.ui.mockUIRestaurant
import org.junit.Assert.*
import org.junit.Test

class RestaurantsDiffCallbackTest {

    private val oldRestaurants = listOf(
        mockUIRestaurant(id = 1L, name = "Burger Stand"),
        mockUIRestaurant(id = 2L, name = "Taco Stand", foodDescription = "Mexican"),
        mockUIRestaurant(id = 3L, name = "Salad Stand", status = "Closed")
    )

    private val newRestaurants = listOf(
        mockUIRestaurant(id = 1L, name = "Burger Stand"),
        mockUIRestaurant(id = 2L, name = "Taco Stand", foodDescription = "Tex-Mex"),
        mockUIRestaurant(id = 3L, name = "Salad Stand", status = "Open"),
        mockUIRestaurant(id = 4L, name = "BBQ Stand")
    )

    private val diffCallback = RestaurantsDiffCallback(oldRestaurants, newRestaurants)

    @Test
    fun getOldListSize_result() {
        assertEquals(diffCallback.oldListSize, oldRestaurants.size)
    }

    @Test
    fun getNewListSize_result() {
        assertEquals(diffCallback.newListSize, newRestaurants.size)
    }

    @Test
    fun areItemsTheSame_true() {
        assertTrue(diffCallback.areItemsTheSame(1, 1))
    }

    @Test
    fun areItemsTheSame_false() {
        assertFalse(diffCallback.areItemsTheSame(0, 1))
    }

    @Test
    fun areContentsTheSame_true() {
        assertTrue(diffCallback.areContentsTheSame(0, 0))
    }

    @Test
    fun areContentsTheSame_false() {
        assertFalse(diffCallback.areContentsTheSame(1, 1))
        assertFalse(diffCallback.areContentsTheSame(2, 2))
    }
}