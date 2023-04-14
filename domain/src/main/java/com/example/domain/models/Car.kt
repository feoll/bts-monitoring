package com.example.domain.models

data class Car(
    val changedDate: String,
    val orderId: String,
    val registrationDate: String,
    val regnum: String,
    val status: Status,
    val typeQueue: TypeQueue
)