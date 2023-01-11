package com.example.bts_monitoring.di.domain

import com.example.domain.repository.ZoneRepository
import com.example.domain.usecases.GetZoneQueueByIdUseCase
import com.example.domain.usecases.GetZonesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    fun provideGetZoneQueueByIdUseCase(zoneRepository: ZoneRepository): GetZoneQueueByIdUseCase {
        return GetZoneQueueByIdUseCase(zoneRepository)
    }

    @Provides
    fun provideGetZonesUseCase(zoneRepository: ZoneRepository): GetZonesUseCase {
        return GetZonesUseCase(zoneRepository)
    }

}