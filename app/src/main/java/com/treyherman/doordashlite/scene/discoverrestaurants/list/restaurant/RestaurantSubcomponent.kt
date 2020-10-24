package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import com.treyherman.doordashlite.di.scope.ViewScope

import dagger.Subcomponent
import dagger.android.AndroidInjector

@ViewScope
@Subcomponent(modules = [RestaurantModule::class])
interface RestaurantSubcomponent : AndroidInjector<RestaurantView> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<RestaurantView>
}
