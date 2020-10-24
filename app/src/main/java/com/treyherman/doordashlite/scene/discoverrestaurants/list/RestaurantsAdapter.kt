package com.treyherman.doordashlite.scene.discoverrestaurants.list

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import com.treyherman.commonui.list.AbsItemsAdapter
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant.RestaurantSubcomponent
import com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant.RestaurantView
import com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant.RestaurantViewHolder
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant

class RestaurantsAdapter(
    context: Context,
    private val restaurantSubcomponentFactory: RestaurantSubcomponent.Factory
) : AbsItemsAdapter<UIRestaurant, RestaurantViewHolder>(context) {
    override fun createViewHolderFromView(view: View, viewType: Int): RestaurantViewHolder {
        (view as? RestaurantView)?.let {
            it.inject(restaurantSubcomponentFactory.create(it))
            return RestaurantViewHolder(it)
        } ?: throw IllegalStateException("unknown view")
    }

    @LayoutRes
    override fun itemLayoutResource(viewType: Int): Int {
        return R.layout.item_restaurant
    }

    override fun itemViewType(position: Int): Int {
        return 0
    }

    override fun onBind(viewHolder: RestaurantViewHolder, data: UIRestaurant, position: Int) {
        viewHolder.onBind(data)
    }
}