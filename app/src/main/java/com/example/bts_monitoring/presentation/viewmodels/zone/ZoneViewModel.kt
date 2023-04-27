package com.example.bts_monitoring.presentation.viewmodels.zone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Zone
import com.example.domain.usecases.settings.GetObserverZoneIdUseCase
import com.example.domain.usecases.settings.SaveObserverZoneIdUseCase
import com.example.domain.usecases.zone.GetZonesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneViewModel @Inject constructor(
    private val getZonesUseCase: GetZonesUseCase,
    private val getObserverZoneIdUseCase: GetObserverZoneIdUseCase,
    private val saveObserverZoneIdUseCase: SaveObserverZoneIdUseCase
) : ViewModel() {

    private val _zones = MutableLiveData<Result<List<Zone>>>()
    private val _observerZoneId = MutableLiveData<String>()

    val zones: LiveData<Result<List<Zone>>> get() = _zones
    val observerZoneId: LiveData<String> get() = _observerZoneId

    init {
        loadZones()
        loadObserverZoneId()
    }

    fun loadZones() {
        viewModelScope.launch {
            _zones.value = getZonesUseCase()!!
        }
    }

    fun loadObserverZoneId() {
        _observerZoneId.value = getObserverZoneIdUseCase()
    }

    fun saveObserverZoneId(id: String) {
        saveObserverZoneIdUseCase(id)
    }

    fun saveAndLoadObserverZoneId(id: String) {
        saveObserverZoneId(id)
        _observerZoneId.value = id
    }
}