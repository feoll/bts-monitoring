package com.example.domain.usecases.settings

import com.example.domain.repository.SettingsRepository

class GetRegNumberUseCase(private val repository: SettingsRepository) {
    operator fun invoke(): String = repository.getRegNumber()
}
