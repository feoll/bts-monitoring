package com.example.bts_monitoring.presentation.service

import com.example.domain.models.Car
import com.example.domain.usecases.settings.GetCarTypeUseCase
import com.example.domain.usecases.settings.GetObserverZoneIdUseCase
import com.example.domain.usecases.settings.GetRegNumberUseCase
import com.example.domain.usecases.zone.GetLiveQueueByCarTypeUseCase
import com.example.domain.usecases.zone.GetPriorityQueueByCarTypeUseCase
import com.example.domain.usecases.zone.GetZoneQueueByIdUseCase
import javax.inject.Inject

class QueueObserverHelper @Inject constructor(
    private val getZonesQueueByIdUseCase: GetZoneQueueByIdUseCase,
    private val getPriorityQueueByCarTypeUseCase: GetPriorityQueueByCarTypeUseCase,
    private val getLiveQueueByCarTypeUseCase: GetLiveQueueByCarTypeUseCase,
    private val getObserverZoneIdUseCase: GetObserverZoneIdUseCase,
    private val getRegNumberUseCase: GetRegNumberUseCase,
    private val getCarTypeUseCase: GetCarTypeUseCase
) {

    var regNumber: String = ""


    private suspend fun getObserverCarQueue(): Result<List<Car>> =
        getZonesQueueByIdUseCase(id = getObserverZoneIdUseCase()).fold(onSuccess = { zoneQueue ->
            val carType = getCarTypeUseCase()
            Result.success(
                getLiveQueueByCarTypeUseCase(carType, zoneQueue)
                        + getPriorityQueueByCarTypeUseCase(carType, zoneQueue)
            )
        }, onFailure = {
            Result.failure(exception = it)
        })


    suspend fun getObserverCar(): Result<Car?> = getObserverCarQueue().fold(
        onSuccess = { cars ->
            regNumber = getRegNumberUseCase()
            Result.success(cars.findLast { it.regnum == regNumber })
        },
        onFailure = { Result.failure(exception = it) })
}