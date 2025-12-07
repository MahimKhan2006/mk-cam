package com.mahim.mkcam.di

import android.content.Context
import com.mahim.mkcam.ai.data.cloud.CloudEnhancer
import com.mahim.mkcam.ai.data.presets.PresetManager
import com.mahim.mkcam.ai.data.tflite.TFLiteEnhancer
import com.mahim.mkcam.core.data.SecureKeyStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSecureKeyStore(@ApplicationContext context: Context): SecureKeyStore {
        return SecureKeyStore(context)
    }

    @Provides
    @Singleton
    fun providePresetManager(@ApplicationContext context: Context): PresetManager {
        return PresetManager(context)
    }

    @Provides
    @Singleton
    @Named("Cloud")
    fun provideCloudEnhancer(keyStore: SecureKeyStore): CloudEnhancer {
        return CloudEnhancer(keyStore)
    }

    @Provides
    @Singleton
    @Named("TFLite")
    fun provideTFLiteEnhancer(@ApplicationContext context: Context): TFLiteEnhancer {
        return TFLiteEnhancer(context)
    }
}
