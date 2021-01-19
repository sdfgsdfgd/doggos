package com.example.nine.di

import com.example.nine.data.DogsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object DataModule {
    private const val BASE_URL = "https://dog.ceo/"

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitService(retrofit: Retrofit): DogsService {
        return retrofit.create(DogsService::class.java)
    }
}