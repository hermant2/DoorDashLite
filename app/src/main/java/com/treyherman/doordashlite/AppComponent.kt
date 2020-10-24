package com.treyherman.doordashlite

import com.treyherman.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        AndroidModule::class,
        AppModule::class,
        ActivityInjectorModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindApplication(application: DoorDashLiteApplication): Builder

        fun build(): AppComponent
    }

    fun inject(application: DoorDashLiteApplication)
}