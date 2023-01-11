package com.example.data.mappers

import com.example.data.entities.*
import com.example.domain.entities.*


fun CarDto.toDomain(): Car {
    val statusDomain: Status = when(status) {
        2 -> Status.ARRIVED
        3 -> Status.CALLED
        else -> Status.UNKNOWN
    }
    val typeQueueDomain: TypeQueue = when(typeQueue) {
        3 -> TypeQueue.LIVE
        else -> TypeQueue.UNKNOWN
    }
    return Car(
        changedDate = changedDate,
        orderId = orderId,
        registrationDate = registrationDate,
        regnum = regnum,
        status = statusDomain,
        typeQueue = typeQueueDomain
    )
}

fun ZoneDto.toDomain(): Zone {
    return Zone(
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
}

fun ZoneInfoDto.toDomain(): ZoneInfo {
    return ZoneInfo(
        id = id,
        name = name,
        nameEn = nameEn,
        phone = phone,
        address = address
    )
}

fun ZoneResultDto.toDomain(): List<Zone> {
    return result.map { zone ->
        zone.toDomain()
    }
}


fun List<CarDto>.toDomain(): List<Car> {
    return this.map { car ->
        car.toDomain()
    }
}

fun ZoneQueueDto.toDomain(): ZoneQueue {
    return ZoneQueue(
        busLiveQueue = busLiveQueue.toDomain(),
        busPriority = busPriority.toDomain(),
        carLiveQueue = carLiveQueue.toDomain(),
        carPriority = carPriority.toDomain(),
        info = info.toDomain(),
        motorcycleLiveQueue = motorcycleLiveQueue.toDomain(),
        motorcyclePriority = motorcyclePriority.toDomain(),
        truckGpk = truckGpk,
        truckLiveQueue = truckLiveQueue.toDomain(),
        truckPriority = truckLiveQueue.toDomain()
    )
}