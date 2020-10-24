package com.treyherman.doordashlite.scene.discoverrestaurants

import com.treyherman.doordashlite.di.scope.ActivityScope

import dagger.Binds
import dagger.Module

@Module
interface DiscoverRestaurantsBindModule {

    @Binds
    @ActivityScope
    fun bindView(activity: DiscoverRestaurantsActivity): DiscoverRestaurantsMvp.View
}
