package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import com.treyherman.doordashlite.di.scope.ViewScope
import dagger.Module
import dagger.Provides

@Module(includes = [RestaurantBindModule::class])
class RestaurantModule {

    @Provides
    @ViewScope
    fun providePresenter(presenter: RestaurantPresenter): RestaurantMvp.Presenter {
        return presenter
    }
}
