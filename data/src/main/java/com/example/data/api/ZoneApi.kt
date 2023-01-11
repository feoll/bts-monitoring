package com.example.data.api

import com.example.data.common.ZONES_TOKEN
import com.example.data.common.ZONE_QUEUE_TOKEN
import com.example.data.entities.ZoneQueueDto
import com.example.data.entities.ZoneResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ZoneApi {

    @GET("/info/checkpoint?token=$ZONES_TOKEN")
    suspend fun getZones(): ZoneResultDto

    @GET("/info/monitoring-new?token=$ZONE_QUEUE_TOKEN")
    suspend fun getZoneQueueById(
        @Query("checkpointId")
        id: String
    ): ZoneQueueDto

}