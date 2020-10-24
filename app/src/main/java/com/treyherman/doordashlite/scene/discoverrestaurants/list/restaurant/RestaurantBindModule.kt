package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import com.treyherman.doordashlite.di.scope.ViewScope
import dagger.Binds
import dagger.Module

@Module
interface RestaurantBindModule {

    @Binds
    @ViewScope
    fun bindView(fragment: RestaurantView): RestaurantMvp.View
}
