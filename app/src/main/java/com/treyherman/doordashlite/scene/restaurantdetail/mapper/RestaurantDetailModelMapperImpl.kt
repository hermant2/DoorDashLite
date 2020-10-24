package com.treyherman.doordashlite.scene.restaurantdetail.mapper

import android.content.res.Resources
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.manager.currency.CurrencyFormatManager
import com.treyherman.doordashlite.scene.restaurantdetail.model.UIRestaurantDetail
import com.treyherman.network.rest.model.response.RestaurantResponse
import javax.inject.Inject

class RestaurantDetailModelMapperImpl @Inject constructor(
    private val currencyFormatManager: CurrencyFormatManager,
    private val resources: Resources
) : RestaurantDetailModelMapper {
    override fun mapRestaurantDetail(response: RestaurantResponse): UIRestaurantDetail {
        val formattedDeliveryCurrency = currencyFormatManager.formatCurrency(response.deliveryFee)
        return UIRestaurantDetail(
            response.name,
            response.description,
            response.coverImgUrl,
            response.status,
            resources.getString(R.string.formatted_delivery_fee, formattedDeliveryCurrency)
        )
    }
}