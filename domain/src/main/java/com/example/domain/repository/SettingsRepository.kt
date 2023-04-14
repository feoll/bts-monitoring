package com.example.domain.repository

import com.example.domain.models.TypeCar


interface SettingsRepository {
    fun saveRegNumber(number: String): Boolean
    fun getRegNumber(): String
    fun saveCarType(type: TypeCar): Boolean
    fun getCarType(): TypeCar
    fun saveObserverZoneId(id: String): Boolean
    fun getObserverZoneId(): String
}