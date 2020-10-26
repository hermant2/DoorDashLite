package com.treyherman.doordashlite.scene.restaurantdetail.mapper

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.mock
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.manager.currency.CurrencyFormatManager
import com.treyherman.doordashlite.mocks.response.mockRestaurantResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantDetailModelMapperTest {

    companion object {
        private const val FORMATTED_FEE = "$2.OO Delivery fee"
    }

    private val mockRestaurantResponse by lazy {
        mockRestaurantResponse(deliveryFee = 200)
    }

    private val currencyFormatManager = mock<CurrencyFormatManager> {
        on { formatCurrency(200) }.thenReturn("$2.00")
    }
    private val resources = mock<Resources> {
        on { getString(R.string.formatted_delivery_fee, "$2.00") }.thenReturn(FORMATTED_FEE)
    }

    private val mapper: RestaurantDetailModelMapper by lazy {
        RestaurantDetailModelMapperImpl(currencyFormatManager, resources)
    }

    @Test
    fun mapRestaurantDetail_result() {
        val detail = mapper.mapRestaurantDetail(mockRestaurantResponse)
        assertEquals(detail.name, mockRestaurantResponse.name)
        assertEquals(detail.description, mockRestaurantResponse.description)
        assertEquals(detail.imageUrl, mockRestaurantResponse.coverImgUrl)
        assertEquals(detail.status, mockRestaurantResponse.status)
        assertEquals(detail.formattedDeliveryFee, FORMATTED_FEE)
    }
}
