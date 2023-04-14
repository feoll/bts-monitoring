package com.example.data.mappers

import com.example.data.models.TypeCarData
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