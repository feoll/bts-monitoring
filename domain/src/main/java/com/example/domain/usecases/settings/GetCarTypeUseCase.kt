package com.example.domain.usecases.settings

import com.example.domain.models.TypeCar
import com.example.domain.repository.SettingsRepository

class GetCarTypeUseCase(private val repository: SettingsRepository) {
    operator fun invoke(): TypeCar = repository.getCarType()
}