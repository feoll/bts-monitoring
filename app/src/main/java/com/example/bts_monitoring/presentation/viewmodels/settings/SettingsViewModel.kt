package com.example.bts_monitoring.presentation.viewmodels.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.TypeAppTheme
import com.example.domain.models.TypeCar
import com.example.domain.usecases.settings.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveCarTypeUseCase: SaveCarTypeUseCase,
    private val saveRegNumberUseCase: SaveRegNumberUseCase,
    private val saveTypeAppThemeUseCase: SaveTypeAppThemeUseCase,
    private val getCarTypeUseCase: GetCarTypeUseCase,
    private val getRegNumberUseCase: GetRegNumberUseCase,
    private val getTypeAppThemeUseCase: GetTypeAppThemeUseCase
): ViewModel() {

    private val _regNumber = MutableLiveData<String>()
    private val _carType = MutableLiveData<TypeCar>()
    private val _appTheme = MutableLiveData<TypeAppTheme>()

    val regNumber: LiveData<String> get() = _regNumber
    val carType: LiveData<TypeCar> get() = _carType
    val appTheme: LiveData<TypeAppTheme> get() = _appTheme

    fun saveRegNumber(number: String) = saveRegNumberUseCase(number = number)

    fun saveCarType(type: TypeCar) = saveCarTypeUseCase(typeCar = type)

    fun saveTypeAppTheme(typeTheme: TypeAppTheme) = saveTypeAppThemeUseCase(typeTheme = typeTheme)

    fun loadRegNumber() {
        _regNumber.value = getRegNumberUseCase()
    }

    fun loadCarType(){
        _carType.value = getCarTypeUseCase()
    }

    fun loadAppTheme() {
        _appTheme.value = getTypeAppThemeUseCase()
    }
}