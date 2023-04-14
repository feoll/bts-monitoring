package com.example.bts_monitoring.di.data

import android.content.Context
import com.example.data.api.ZoneApi
import com.example.data.common.BASE_URL
import com.example.data.repository.SettingsRepositoryImpl
import com.example.data.repository.ZoneRepositoryImpl
import com.example.data.source.SettingsDataSource
import com.example.data.source.SettingsDataSourceImpl
import com.example.data.source.ZoneDataSource
import com.example.data.source.ZoneRemoteDataSourceImpl
import com.example.domain.repository.SettingsRepository
import com.example.domain.repository.ZoneRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideZoneApi(retrofit: Retrofit): ZoneApi {
        return retrofit.create(ZoneApi::class.java)
    }

    @Provides
    fun provideZoneRemoteDataSource(api: ZoneApi): ZoneDataSource.Remote {
        return ZoneRemoteDataSourceImpl(api = api)
    }

    @Provides
    fun provideZoneRepository(remote: ZoneDataSource.Remote): ZoneRepository {
        return ZoneRepositoryImpl(remote = remote)
    }

    @Singleton
    @Provides
    fun provideSettingsDataSource(@ApplicationContext context: Context): SettingsDataSource {
        return SettingsDataSourceImpl(context = context)
    }

    @Singleton
    @Provides
    fun provideSettingsRepository(source: SettingsDataSource): SettingsRepository {
        return SettingsRepositoryImpl(settingsDataSource = source)
    }
}