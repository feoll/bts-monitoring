package com.example.bts_monitoring.presentation.binding

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.bts_monitoring.R
import com.example.domain.models.Car
import com.example.domain.models.Status

class RowZoneDetailsCustomBinding {
    companion object {
        @BindingAdapter("carStatus")
        @JvmStatic
        fun carStatus(view: TextView, car: Car) {
            view.text = when (car.status) {
                Status.ARRIVED -> "Прибыл"
                Status.CALLED -> "Вызван"
                Status.CANCELLED -> "Аннулирован"
                Status.UNKNOWN -> "Неизвестно"
            }
        }

        @BindingAdapter("statusColor")
        @JvmStatic
        fun statusColor(view: View, car: Car) {
            view.setBackgroundColor(
                when (car.status) {
                    Status.ARRIVED -> ContextCompat.getColor(view.context, R.color.general)
                    Status.CALLED -> ContextCompat.getColor(view.context, R.color.yellow)
                    Status.CANCELLED -> ContextCompat.getColor(view.context, R.color.red)
                    Status.UNKNOWN -> ContextCompat.getColor(view.context, R.color.general)
                }
            )
        }
    }
}