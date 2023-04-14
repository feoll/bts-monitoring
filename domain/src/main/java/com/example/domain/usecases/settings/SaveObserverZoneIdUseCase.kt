package com.example.domain.usecases.settings

import com.example.domain.repository.SettingsRepository

class SaveObserverZoneIdUseCase(private val repository: SettingsRepository) {
    operator fun invoke(id: String): Boolean = repository.saveObserverZoneId(id = id)
}