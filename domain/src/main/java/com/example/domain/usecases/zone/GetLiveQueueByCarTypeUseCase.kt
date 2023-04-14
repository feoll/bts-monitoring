package com.example.domain.usecases.zone

import com.example.domain.models.Car
import com.example.domain.models.TypeCar
import com.example.domain.models.ZoneQueue

class GetLiveQueueByCarTypeUseCase {
    operator fun invoke(typeCar: TypeCar, zoneQueue: ZoneQueue): List<Car> =
        when (typeCar) {
            TypeCar.PASSENGER -> zoneQueue.carLiveQueue
            TypeCar.TRUCK -> zoneQueue.truckLiveQueue
            TypeCar.BUS -> zoneQueue.busLiveQueue
            TypeCar.MOTORCYCLE -> zoneQueue.motorcycleLiveQueue
            else -> emptyList()
        }
}