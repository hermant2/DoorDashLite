package com.treyherman.doordashlite.scene.restaurantdetail

import com.treyherman.doordashlite.di.scope.ActivityScope
import dagger.Binds
import dagger.Module

@Module
interface RestaurantDetailBindModule {

    @Binds
    @ActivityScope
    fun bindView(activity: RestaurantDetailActivity): RestaurantDetailMvp.View
}
