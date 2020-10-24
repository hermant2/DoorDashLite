package com.treyherman.doordashlite

import com.treyherman.doordashlite.di.scope.ActivityScope
import com.treyherman.doordashlite.scene.discoverrestaurants.DiscoverRestaurantsActivity
import com.treyherman.doordashlite.scene.discoverrestaurants.DiscoverRestaurantsModule
import com.treyherman.doordashlite.scene.restaurantdetail.RestaurantDetailActivity
import com.treyherman.doordashlite.scene.restaurantdetail.RestaurantDetailModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityInjectorModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [DiscoverRestaurantsModule::class])
    fun contributesDiscoverRestaurantsActivity(): DiscoverRestaurantsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [RestaurantDetailModule::class])
    fun contributesRestaurantDetailActivity(): RestaurantDetailActivity
}
