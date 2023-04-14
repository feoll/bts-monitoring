package com.example.data.mappers

import com.example.data.models.*
import com.example.domain.models.*


fun CarDto.toDomain(): Car {
    val statusDomain: Status = when (status) {
        2 -> Status.ARRIVED
        3 -> Status.CALLED
        9 -> Status.CANCELLED
        else -> Status.UNKNOWN
    }
    val typeQueueDomain: TypeQueue = when (typeQueue) {
        1 -> TypeQueue.PRIORITY
        3 -> TypeQueue.LIVE
        else -> TypeQueue.UNKNOWN
    }
    return Car(
        changedDate = changedDate,
        orderId = if (orderId.isNullOrEmpty()) "" else orderId,
        registrationDate = registrationDate,
        regnum = regnum,
        status = statusDomain,
        typeQueue = typeQueueDomain
    )
}

fun ZoneDto.toDomain(): Zone = Zone(
    id = id,
    name = name,
    phone = phone,
    address = address,
    countAll = countAll,
    countBookings = countBookings,
    countBus = countBus,
    countCar = countCar,
    countLiveQueue = countLiveQueue,
    countMotorcycle = countMotorcycle,
    countPassedSCC = countPassedSCC,
    countPriority = countPriority,
    countTruck = countTruck
)

fun ZoneInfoDto.toDomain(): ZoneInfo = ZoneInfo(
    id = id,
    name = name,
    nameEn = nameEn,
    phone = phone,
    address = address
)

fun ZoneResultDto.toDomain(): List<Zone> = result.map { zone ->
    zone.toDomain()
}


fun List<CarDto>.toDomain(): List<Car> = this.map { car ->
    car.toDomain()
}


fun ZoneQueueDto.toDomain(): ZoneQueue = ZoneQueue(
    busLiveQueue = busLiveQueue.toDomain(),
    busPriority = busPriority.toDomain(),
    carLiveQueue = carLiveQueue.toDomain(),
    carPriority = carPriority.toDomain(),
    info = info!!.toDomain(),
    motorcycleLiveQueue = motorcycleLiveQueue.toDomain(),
    motorcyclePriority = motorcyclePriority.toDomain(),
    truckGpk = truckGpk,
    truckLiveQueue = truckLiveQueue.toDomain(),
    truckPriority = truckPriority.toDomain()
)