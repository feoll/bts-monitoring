package com.example.data.models

import com.example.data.common.ValueDeserializer
import com.google.gson.annotations.Expose
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

data class CarDto(
    @SerializedName("changed_date")
    val changedDate: String,
    @SerializedName("order_id")
    @Expose
    @JsonAdapter(ValueDeserializer::class)
    val orderId: String?,
    @SerializedName("registration_date")
    val registrationDate: String,
    val regnum: String,
    val status: Int,
    @SerializedName("type_queue")
    val typeQueue: Int
)