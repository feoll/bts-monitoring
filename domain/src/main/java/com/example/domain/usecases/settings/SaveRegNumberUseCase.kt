package com.example.domain.usecases.settings

import com.example.domain.repository.SettingsRepository

class SaveRegNumberUseCase(private val repository: SettingsRepository) {
    operator fun invoke(number: String): Boolean = repository.saveRegNumber(number = number)
}