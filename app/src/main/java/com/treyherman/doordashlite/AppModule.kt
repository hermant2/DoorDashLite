package com.treyherman.doordashlite

import android.app.Application
import android.content.Context
import com.treyherman.doordashlite.manager.currency.CurrencyFormatManager
import com.treyherman.doordashlite.manager.currency.CurrencyFormatManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: DoorDashLiteApplication): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideApplication(application: DoorDashLiteApplication): Application {
        return application
    }

    @Provides
    fun provideCurrencyFormatManager(
        currencyFormatManager: CurrencyFormatManagerImpl
    ): CurrencyFormatManager {
        return currencyFormatManager
    }
}
