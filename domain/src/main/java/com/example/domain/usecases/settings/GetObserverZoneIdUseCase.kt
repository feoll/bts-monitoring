package com.example.domain.usecases.settings

import com.example.domain.repository.SettingsRepository

class GetObserverZoneIdUseCase(private val repository: SettingsRepository) {
    operator fun invoke(): String = repository.getObserverZoneId()
}