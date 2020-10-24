package com.treyherman.doordashlite.scene.restaurantdetail

import android.content.Context
import coil.ImageLoader
import com.treyherman.doordashlite.di.scope.ActivityScope
import com.treyherman.doordashlite.scene.restaurantdetail.mapper.RestaurantDetailModelMapper
import com.treyherman.doordashlite.scene.restaurantdetail.mapper.RestaurantDetailModelMapperImpl
import com.treyherman.network.image.ImageLoaderProvider
import com.treyherman.network.rest.service.restaurant.RestaurantsService
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [RestaurantDetailBindModule::class])
class RestaurantDetailModule {

    @Provides
    @ActivityScope
    fun providePresenter(
        view: RestaurantDetailMvp.View,
        restaurantsService: RestaurantsService,
        mapper: RestaurantDetailModelMapper
    ): RestaurantDetailMvp.Presenter {
        return RestaurantDetailPresenter(view, restaurantsService, mapper, CompositeDisposable())
    }

    @Provides
    @ActivityScope
    fun provideRestaurantDetailModelMapper(
        mapper: RestaurantDetailModelMapperImpl
    ): RestaurantDetailModelMapper {
        return mapper
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
