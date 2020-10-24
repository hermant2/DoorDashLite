package com.treyherman.network.rest

import com.treyherman.network.rest.service.restaurant.RestaurantsService
import com.treyherman.network.rest.service.restaurant.RestaurantsServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RestApiServiceModule {
    @Provides
    fun provideRestaurantService(service: RestaurantsServiceImpl): RestaurantsService = service
}
