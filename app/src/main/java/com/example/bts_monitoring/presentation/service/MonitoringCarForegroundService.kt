package com.example.bts_monitoring.presentation.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.bts_monitoring.R
import com.example.bts_monitoring.presentation.activities.MainActivity
import com.example.domain.models.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MonitoringCarForegroundService : Service() {

    @Inject
    lateinit var queueObserverHelper: QueueObserverHelper

    private var isServiceStarted = false
    private var notificationManager: NotificationManager? = null
    private var job: Job? = null

    private val builder by lazy {
        NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.monitoring_car))
            .setGroup(getString(R.string.monitoring))
            .setGroupSummary(false)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(getPendingIntent())
            .setSilent(true)
            .setSmallIcon(R.drawable.ic_search)
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        processCommand(intent)
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun processCommand(intent: Intent?) {
        when (intent?.extras?.getString(COMMAND_ID) ?: INVALID) {
            COMMAND_START -> commandStart()
            COMMAND_STOP -> commandStop()
            INVALID -> return
        }
    }

    private fun commandStart() {
        if (isServiceStarted) {
            return
        }
        Log.i("TAG", "commandStart()")
        try {
            moveToStartedState()
            startForegroundAndShowNotification()
            continueObserver()
        } finally {
            isServiceStarted = true
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun continueObserver() {
        job = GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                notificationManager?.notify(
                    NOTIFICATION_ID,
                    getNotification(
                        title = queueObserverHelper.regNumber,
                        content = buildContentNotification()
                    )
                )
                delay(INTERVAL)
            }
        }
    }

    //    System.currentTimeMillis().toString() +
    private fun commandStop() {
        if (!isServiceStarted) {
            return
        }
        Log.i("TAG", "commandStop()")
        try {
            job?.cancel()
            stopForeground(STOP_FOREGROUND_REMOVE)
            stopSelf()
        } finally {
            isServiceStarted = false
        }
    }

    private fun moveToStartedState() {
        startForegroundService(Intent(this, MonitoringCarForegroundService::class.java))
    }

    private fun startForegroundAndShowNotification() {
        createChannel()
        val notification = getNotification(getString(R.string.monitoring_car), "")
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun getNotification(title: String, content: String) =
        builder.setContentText(content)
            .setContentTitle(getString(R.string.monitoring_car) + " " + title).build()


    private fun createChannel() {
        val channelName = CHANNEL_NAME
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val notificationChannel = NotificationChannel(
            CHANNEL_ID, channelName, importance
        )
        notificationManager?.createNotificationChannel(notificationChannel)
    }

    private fun getPendingIntent(): PendingIntent? {
        val resultIntent = Intent(this, MainActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    private suspend fun buildContentNotification(): String {
        return queueObserverHelper.getObserverCar().fold(onSuccess = { car ->
            if (car == null) {
                getString(R.string.error_get_information_queue)
            } else {
                val status = when (car.status) {
                    Status.ARRIVED -> getString(R.string.arrived)
                    Status.CALLED -> getString(R.string.called)
                    Status.CANCELLED -> getString(R.string.cancelled)
                    Status.UNKNOWN -> getString(R.string.unknown)
                }
                if (car.orderId.isEmpty()) {
                    getString(R.string.status_notification) + " " + status
                } else {
                    getString(R.string.position_notification) + " " + car.orderId + "\n" + getString(
                        R.string.status_notification
                    ) + " " + status
                }
            }
        }, onFailure = { getString(R.string.error_get_data) })
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "Monitoring"
        private const val NOTIFICATION_ID = 777
        private const val INTERVAL = 1000L

        const val INVALID = "INVALID"
        const val COMMAND_START = "COMMAND_START"
        const val COMMAND_STOP = "COMMAND_STOP"
        const val COMMAND_ID = "COMMAND_ID"
    }
}