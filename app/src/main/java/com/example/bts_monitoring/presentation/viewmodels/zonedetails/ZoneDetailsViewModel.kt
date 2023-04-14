package com.example.bts_monitoring.presentation.viewmodels.zonedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.*
import com.example.domain.usecases.zone.GetLiveQueueByCarTypeUseCase
import com.example.domain.usecases.zone.GetPriorityQueueByCarTypeUseCase
import com.example.domain.usecases.zone.GetZoneQueueByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ZoneDetailsViewModel @Inject constructor(
    private val getZoneQueueByIdUseCase: GetZoneQueueByIdUseCase,
    private val getLiveQueueByCarTypeUseCase: GetLiveQueueByCarTypeUseCase,
    private val getPriorityQueueByCarTypeUseCase: GetPriorityQueueByCarTypeUseCase
) : ViewModel() {

    private val _allQueue = MutableLiveData<Result<ZoneQueue>?>()
    private val _carQueue = MutableLiveData<Result<List<Car>>?>()
    val allQueue: LiveData<Result<ZoneQueue>?> get() = _allQueue
    val carQueue: LiveData<Result<List<Car>>?> get() = _carQueue

    val typeQueue = MutableLiveData<TypeQueue>()
    val status = MutableLiveData<Status>()
    val typeCar = MutableLiveData<TypeCar>()

    init {
        typeQueue.value = TypeQueue.LIVE
        status.value = Status.UNKNOWN
        typeCar.value = TypeCar.TRUCK
    }

    fun clearQueues() {
        _allQueue.value = null
        _carQueue.value = null
    }

    fun loadAllQueue(id: String) {
        viewModelScope.launch {
            _allQueue.value = getZoneQueueByIdUseCase.invoke(id)
        }
    }

    fun loadSortedCarQueue() {
        viewModelScope.launch {
            _allQueue.value?.fold(onSuccess = {
                _carQueue.value = Result.success(getSortedQueueList(it))
            }, onFailure = {
                _carQueue.value = Result.failure(it)
            })
        }
    }

    private fun getSortedQueueList(zoneQueue: ZoneQueue): List<Car> {
        var queueList = emptyList<Car>()

        if (typeQueue.value == TypeQueue.LIVE)
            queueList = getLiveQueueByCarTypeUseCase(typeCar.value!!, zoneQueue)
        else if (typeQueue.value == TypeQueue.PRIORITY)
            queueList = getPriorityQueueByCarTypeUseCase(typeCar.value!!, zoneQueue)

        if (status.value == Status.UNKNOWN) return queueList

        return queueList.filter { it.status == status.value }
    }
}
