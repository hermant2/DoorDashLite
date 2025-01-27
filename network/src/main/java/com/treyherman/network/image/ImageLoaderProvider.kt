package com.treyherman.network.image

import android.content.Context
import coil.ImageLoader
import com.treyherman.network.di.qualifier.ImageClient
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ImageLoaderProvider @Inject constructor(@ImageClient private val okHttpClient: OkHttpClient) {
    fun provide(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .build()
    }
}
