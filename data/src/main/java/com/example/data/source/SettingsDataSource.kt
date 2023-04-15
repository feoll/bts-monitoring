package com.example.data.source

import com.example.data.models.TypeCarData
import com.example.data.models.TypeAppTheme

interface SettingsDataSource {
    fun saveRegNumber(number: String): Boolean
    fun getRegNumber(): String
    fun saveCarType(type: TypeCarData): Boolean
    fun getCarType(): TypeCarData
    fun saveObserverZoneId(id: String): Boolean
    fun getObserverZoneId(): String
    fun saveTypeAppTheme(typeTheme: TypeAppTheme): Boolean
    fun getTypeAppTheme(): TypeAppTheme
}