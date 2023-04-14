package com.example.domain.usecases.settings

import com.example.domain.models.TypeCar
import com.example.domain.repository.SettingsRepository

class SaveCarTypeUseCase(private val repository: SettingsRepository) {
    operator fun invoke(typeCar: TypeCar): Boolean = repository.saveCarType(type = typeCar)
}