package com.example.domain.usecases.zone

import com.example.domain.models.Car
import com.example.domain.models.TypeCar
import com.example.domain.models.ZoneQueue

class GetPriorityQueueByCarTypeUseCase {
    operator fun invoke(typeCar: TypeCar, zoneQueue: ZoneQueue): List<Car> =
        when (typeCar) {
            TypeCar.PASSENGER -> zoneQueue.carPriority
            TypeCar.TRUCK -> zoneQueue.truckPriority
            TypeCar.BUS -> zoneQueue.busPriority
            TypeCar.MOTORCYCLE -> zoneQueue.motorcyclePriority
            else -> emptyList()
        }
}