package com.example.nine.di


import com.example.nine.domain.DogsRepository
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {
    @Singleton
    abstract fun bindDogsRepository(): DogsRepository
}
