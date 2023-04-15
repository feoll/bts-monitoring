package com.example.bts_monitoring.presentation.utils.color

import android.content.Context
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

class ColorUtil {
    companion object {
        fun getColor(context: Context, @ColorInt attrColor: Int): Int {
            val typedValue = TypedValue()
            context.theme.resolveAttribute(attrColor, typedValue, true)
            return ContextCompat.getColor(context, typedValue.resourceId)
        }
    }
}