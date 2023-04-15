package com.example.domain.usecases.settings

import com.example.domain.models.TypeAppTheme
import com.example.domain.repository.SettingsRepository

class GetTypeAppThemeUseCase (private val repository: SettingsRepository) {
    operator fun invoke(): TypeAppTheme = repository.getTypeAppTheme()
}