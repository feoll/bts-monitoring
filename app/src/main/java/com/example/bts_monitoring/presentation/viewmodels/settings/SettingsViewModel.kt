package com.example.bts_monitoring.presentation.viewmodels.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.models.TypeCar
import com.example.domain.usecases.settings.GetCarTypeUseCase
import com.example.domain.usecases.settings.GetRegNumberUseCase
import com.example.domain.usecases.settings.SaveCarTypeUseCase
import com.example.domain.usecases.settings.SaveRegNumberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val saveCarTypeUseCase: SaveCarTypeUseCase,
    private val saveRegNumberUseCase: SaveRegNumberUseCase,
    private val getCarTypeUseCase: GetCarTypeUseCase,
    private val getRegNumberUseCase: GetRegNumberUseCase
): ViewModel() {

    private val _regNumber = MutableLiveData<String>()
    private val _carType = MutableLiveData<TypeCar>()

    val regNumber: LiveData<String> get() = _regNumber
    val carType: LiveData<TypeCar> get() = _carType

    fun saveRegNumber(number: String) = saveRegNumberUseCase(number = number)

    fun saveCarType(type: TypeCar) = saveCarTypeUseCase(typeCar = type)

    fun loadRegNumber() {
        _regNumber.value = getRegNumberUseCase()
    }

    fun loadCarType(){
        _carType.value = getCarTypeUseCase()
    }
}