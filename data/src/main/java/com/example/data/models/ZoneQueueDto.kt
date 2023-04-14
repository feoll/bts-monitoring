package com.example.data.models

data class ZoneQueueDto(
    val busLiveQueue: List<CarDto>,
    val busPriority: List<CarDto>,
    val carLiveQueue: List<CarDto>,
    val carPriority: List<CarDto>,
    val info: ZoneInfoDto?,
    val motorcycleLiveQueue: List<CarDto>,
    val motorcyclePriority: List<CarDto>,
    val truckGpk: List<Any>,
    val truckLiveQueue: List<CarDto>,
    val truckPriority: List<CarDto>
)