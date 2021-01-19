package com.example.nine.di

import android.content.Context
import com.example.nine.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideContext(app: App): Context {
        return app
    }
}