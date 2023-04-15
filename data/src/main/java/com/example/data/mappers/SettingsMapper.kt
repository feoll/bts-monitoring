package com.example.data.mappers

import com.example.data.models.TypeCarData
import com.example.domain.models.TypeAppTheme
import com.example.domain.models.TypeCar

fun TypeCarData.toDomain(): TypeCar = try {
    TypeCar.valueOf(name)
} catch (ex: Exception) {
    TypeCar.UNKNOWN
}

fun TypeCar.toData(): TypeCarData = try {
    TypeCarData.valueOf(name)
} catch (ex: Exception) {
    TypeCarData.UNKNOWN
}

fun TypeAppTheme.toData(): com.example.data.models.TypeAppTheme = try {
    com.example.data.models.TypeAppTheme.valueOf(name)
} catch (ex: Exception) {
    com.example.data.models.TypeAppTheme.DARK
}

fun com.example.data.models.TypeAppTheme.toDomain(): TypeAppTheme = try {
    TypeAppTheme.valueOf(name)
} catch (ex: Exception) {
    TypeAppTheme.DARK
}