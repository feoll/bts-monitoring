package com.example.domain.entities

data class ZoneQueue(
    val busLiveQueue: List<Any>,
    val busPriority: List<Any>,
    val carLiveQueue: List<Car>,
    val carPriority: List<Any>,
    val info: ZoneInfo,
    val motorcycleLiveQueue: List<Any>,
    val motorcyclePriority: List<Any>,
    val truckGpk: List<Any>,
    val truckLiveQueue: List<Any>,
    val truckPriority: List<Any>
)