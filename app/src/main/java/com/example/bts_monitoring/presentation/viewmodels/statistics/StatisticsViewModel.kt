package com.example.bts_monitoring.presentation.viewmodels.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Zone
import com.example.domain.usecases.zone.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getZonesUseCase: GetZonesUseCase
): ViewModel() {

    private val _zones = MutableLiveData<Result<List<Zone>>>()
    val zones: LiveData<Result<List<Zone>>> get() = _zones


    fun getZones() {
        viewModelScope.launch {
            _zones.value = getZonesUseCase.invoke()
        }
    }
}