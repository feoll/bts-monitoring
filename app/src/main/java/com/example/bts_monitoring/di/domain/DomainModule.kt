package com.example.bts_monitoring.di.domain

import com.example.domain.repository.SettingsRepository
import com.example.domain.repository.ZoneRepository
import com.example.domain.usecases.settings.*
import com.example.domain.usecases.zone.GetLiveQueueByCarTypeUseCase
import com.example.domain.usecases.zone.GetPriorityQueueByCarTypeUseCase
import com.example.domain.usecases.zone.GetZoneQueueByIdUseCase
import com.example.domain.usecases.zone.GetZonesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun provideGetZoneQueueByIdUseCase(zoneRepository: ZoneRepository): GetZoneQueueByIdUseCase {
        return GetZoneQueueByIdUseCase(repository = zoneRepository)
    }
    @Provides
    fun provideGetZonesUseCase(zoneRepository: ZoneRepository): GetZonesUseCase {
        return GetZonesUseCase(repository = zoneRepository)
    }

    @Provides
    fun provideGetCarTypeUseCase(settingsRepository: SettingsRepository): GetCarTypeUseCase {
        return GetCarTypeUseCase(repository = settingsRepository)
    }

    @Provides
    fun provideGetObserverZoneIdUseCase(settingsRepository: SettingsRepository): GetObserverZoneIdUseCase {
        return GetObserverZoneIdUseCase(repository = settingsRepository)
    }

    @Provides
    fun provideGetRegNumberUseCase(settingsRepository: SettingsRepository): GetRegNumberUseCase {
        return GetRegNumberUseCase(repository = settingsRepository)
    }

    @Provides
    fun provideSaveCarTypeUseCase(settingsRepository: SettingsRepository): SaveCarTypeUseCase {
        return SaveCarTypeUseCase(repository = settingsRepository)
    }

    @Provides
    fun provideSaveObserverZoneIdUseCase(settingsRepository: SettingsRepository): SaveObserverZoneIdUseCase {
        return SaveObserverZoneIdUseCase(repository = settingsRepository)
    }

    @Provides
    fun provideSaveRegNumberUseCase(settingsRepository: SettingsRepository): SaveRegNumberUseCase {
        return SaveRegNumberUseCase(repository = settingsRepository)
    }

    @Singleton
    @Provides
    fun provideSaveTypeAppThemeUseCase(settingsRepository: SettingsRepository): SaveTypeAppThemeUseCase {
        return SaveTypeAppThemeUseCase(repository = settingsRepository)
    }

    @Singleton
    @Provides
    fun provideGetTypeAppThemeUseCase(settingsRepository: SettingsRepository): GetTypeAppThemeUseCase {
        return GetTypeAppThemeUseCase(repository = settingsRepository)
    }

    @Singleton
    @Provides
    fun provideGetLiveQueueByCarTypeUseCase(): GetLiveQueueByCarTypeUseCase {
        return GetLiveQueueByCarTypeUseCase()
    }

    @Singleton
    @Provides
    fun provideGetPriorityQueueByCarTypeUseCase(): GetPriorityQueueByCarTypeUseCase {
        return GetPriorityQueueByCarTypeUseCase()
    }
}