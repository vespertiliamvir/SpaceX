package com.example.spacex.di

import com.example.spacex.data.network.retrofit.RetrofitInstance
import com.example.spacex.data.network.retrofit.SpaceXService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit = RetrofitInstance().retrofit

    @Singleton
    @Provides
    fun providesSpaceXService(retrofit: Retrofit) = retrofit.create(SpaceXService::class.java)
}