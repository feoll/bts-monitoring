package com.example.data.source

import com.example.data.models.TypeCarData

interface SettingsDataSource {
    fun saveRegNumber(number: String): Boolean
    fun getRegNumber(): String
    fun saveCarType(type: TypeCarData): Boolean
    fun getCarType(): TypeCarData
    fun saveObserverZoneId(id: String): Boolean
    fun getObserverZoneId(): String
}