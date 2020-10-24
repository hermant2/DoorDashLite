package com.treyherman.doordashlite.scene.discoverrestaurants

import android.content.Context
import android.content.res.Resources
import coil.ImageLoader
import com.treyherman.doordashlite.di.scope.ActivityScope
import com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant.RestaurantSubcomponent
import com.treyherman.doordashlite.scene.discoverrestaurants.mapper.RestaurantsModelMapperImpl
import com.treyherman.network.image.ImageLoaderProvider
import com.treyherman.network.rest.service.restaurant.RestaurantsService

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(
    includes = [DiscoverRestaurantsBindModule::class],
    subcomponents = [RestaurantSubcomponent::class]
)
class DiscoverRestaurantsModule {

    @Provides
    @ActivityScope
    fun providePresenter(
        view: DiscoverRestaurantsMvp.View,
        restaurantsService: RestaurantsService,
        resources: Resources
    ): DiscoverRestaurantsMvp.Presenter {
        return DiscoverRestaurantsPresenter(
            view,
            restaurantsService,
            resources,
            RestaurantsModelMapperImpl(),
            CompositeDisposable()
        )
    }

    @Provides
    @ActivityScope
    fun provideImageLoader(
        imageLoaderProvider: ImageLoaderProvider,
        context: Context
    ): ImageLoader {
        return imageLoaderProvider.provide(context)
    }
}
