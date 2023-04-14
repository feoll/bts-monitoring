package com.example.domain.models

data class Zone(
    val id: String,
    val name: String,
    var phone: String,
    val address: String,
    val countAll: Int,
    val countBookings: Int,
    val countBus: Int,
    val countCar: Int,
    val countLiveQueue: Int,
    val countMotorcycle: Int,
    val countPassedSCC: Int,
    val countPriority: Int,
    val countTruck: Int
)
