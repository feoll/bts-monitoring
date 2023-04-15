package com.example.data.repository

import com.example.data.mappers.toData
import com.example.data.mappers.toDomain
import com.example.data.source.SettingsDataSource
import com.example.domain.models.TypeAppTheme
import com.example.domain.models.TypeCar
import com.example.domain.repository.SettingsRepository

class SettingsRepositoryImpl(private val settingsDataSource: SettingsDataSource) :
    SettingsRepository {
    override fun saveRegNumber(number: String): Boolean = settingsDataSource.saveRegNumber(number = number)

    override fun getRegNumber(): String = settingsDataSource.getRegNumber()

    override fun saveCarType(type: TypeCar): Boolean = settingsDataSource.saveCarType(type = type.toData())

    override fun getCarType(): TypeCar = settingsDataSource.getCarType().toDomain()

    override fun saveObserverZoneId(id: String): Boolean = settingsDataSource.saveObserverZoneId(id = id)

    override fun getObserverZoneId(): String = settingsDataSource.getObserverZoneId()
    override fun saveTypeAppTheme(typeTheme: TypeAppTheme): Boolean = settingsDataSource.saveTypeAppTheme(typeTheme = typeTheme.toData())

    override fun getTypeAppTheme(): TypeAppTheme = settingsDataSource.getTypeAppTheme().toDomain()
}

