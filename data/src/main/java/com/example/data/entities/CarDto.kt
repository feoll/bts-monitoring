package com.example.data.entities

import com.example.data.common.ValueDeserializer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter

data class CarDto(
    val changedDate: String,
    @Expose
    @JsonAdapter(ValueDeserializer::class)
    val orderId: String,
    val registrationDate: String,
    val regnum: String,
    val status: Int,
    val typeQueue: Int
)