package com.example.domain.usecases.settings

import com.example.domain.models.TypeAppTheme
import com.example.domain.repository.SettingsRepository

class SaveTypeAppThemeUseCase (private val repository: SettingsRepository) {
    operator fun invoke(typeTheme: TypeAppTheme): Boolean = repository.saveTypeAppTheme(typeTheme = typeTheme)
}