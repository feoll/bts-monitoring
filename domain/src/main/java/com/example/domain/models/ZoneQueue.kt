package com.example.domain.models

data class ZoneQueue(
    val busLiveQueue: List<Car>,
    val busPriority: List<Car>,
    val carLiveQueue: List<Car>,
    val carPriority: List<Car>,
    val info: ZoneInfo,
    val motorcycleLiveQueue: List<Car>,
    val motorcyclePriority: List<Car>,
    val truckGpk: List<Any>,
    val truckLiveQueue: List<Car>,
    val truckPriority: List<Car>
)