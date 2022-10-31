package com.example.spacex.di

import com.example.spacex.data.network.retrofit.SpaceXService
import com.example.spacex.data.repository.ISpaceXRepository
import com.example.spacex.data.repository.SpaceXRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesSpaceXRepository(spaceXService: SpaceXService): ISpaceXRepository {
        return SpaceXRepository(spaceXService)
    }
}