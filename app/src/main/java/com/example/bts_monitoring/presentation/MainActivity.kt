package com.example.bts_monitoring.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bts_monitoring.R
import com.example.domain.usecases.GetZoneQueueByIdUseCase
import com.example.domain.usecases.GetZonesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CoroutineScope {

    @Inject lateinit var getZonesUseCase: GetZonesUseCase
    @Inject lateinit var getZoneQueueByIdUseCase: GetZoneQueueByIdUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch {
            Log.d("UseCase Test GetZones", getZonesUseCase().toString())
            Log.d("UseCase Test GetZones", getZoneQueueByIdUseCase(id = "53d94097-2b34-11ec-8467-ac1f6bf889c0").toString())
        }
    }

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
}