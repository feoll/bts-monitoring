package com.example.data.source

import android.content.Context
import com.example.data.models.TypeCarData
import com.example.data.models.TypeAppTheme


private const val SHARED_PREFS_NAME = "shared_prefs_settings"
private const val KEY_REG_NUMBER = "reg_number"
private const val KEY_CAR_TYPE = "car_type"
private const val KEY_OBSERVER_ZONE_ID = "observer_zone_id"
private const val KEY_APP_THEME = "app_theme"

class SettingsDataSourceImpl(context: Context) : SettingsDataSource {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveRegNumber(number: String): Boolean {
        sharedPreferences.edit().putString(KEY_REG_NUMBER, number).apply()
        return true
    }

    override fun getRegNumber(): String {
        return sharedPreferences.getString(KEY_REG_NUMBER, "") ?: ""
    }

    override fun saveCarType(type: TypeCarData): Boolean {
        sharedPreferences.edit().putString(KEY_CAR_TYPE, type.name).apply()
        return true
    }

    override fun getCarType(): TypeCarData {
        return TypeCarData.valueOf(
            sharedPreferences.getString(KEY_CAR_TYPE, TypeCarData.PASSENGER.name)
                ?: TypeCarData.PASSENGER.name
        )
    }

    override fun saveObserverZoneId(id: String): Boolean {
        sharedPreferences.edit().putString(KEY_OBSERVER_ZONE_ID, id).apply()
        return true
    }

    override fun getObserverZoneId(): String {
        return sharedPreferences.getString(KEY_OBSERVER_ZONE_ID, "") ?: ""
    }

    override fun saveTypeAppTheme(typeTheme: TypeAppTheme): Boolean {
        sharedPreferences.edit().putString(KEY_APP_THEME, typeTheme.name).apply()
        return true
    }

    override fun getTypeAppTheme(): TypeAppTheme {
        return TypeAppTheme.valueOf(
            sharedPreferences.getString(KEY_APP_THEME, TypeAppTheme.LIGHT.name)
                ?: TypeAppTheme.LIGHT.name
        )
    }
}


