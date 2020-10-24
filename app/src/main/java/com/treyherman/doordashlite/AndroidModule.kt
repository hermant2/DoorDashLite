package com.treyherman.doordashlite

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {
    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}
