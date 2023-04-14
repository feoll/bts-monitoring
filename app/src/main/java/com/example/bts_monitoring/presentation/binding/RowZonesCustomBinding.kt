package com.example.bts_monitoring.presentation.binding

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import com.example.bts_monitoring.R
import com.example.bts_monitoring.presentation.fragments.zone.ZoneFragmentDirections
import com.example.domain.models.Zone


class RowZonesCustomBinding {
    companion object {
        @BindingAdapter("navigateToZoneDetails")
        @JvmStatic
        fun navigateToZoneDetails(view: View, zone: Zone) {
            view.setOnClickListener {
                val action = ZoneFragmentDirections.actionZoneFragmentToZoneDetailsFragment(
                    id = zone.id, label = zone.name
                )
                Navigation.findNavController(it).navigate(action)
            }
        }


        @BindingAdapter(value = ["zone", "observerId"], requireAll = true)
        @JvmStatic
        fun activateAndDeactivateObserverButton(
            button: Button,
            zone: Zone,
            observerId: String
        ) {
            if (observerId.isEmpty()) {
                button.text = button.context.getString(R.string.track)
                return
            }

            if (observerId == zone.id) {
                button.text = button.context.getString(R.string.tracking)
//                button.setBackgroundColor(
//                    ContextCompat.getColor(
//                        button.context,
//                        R.color.md_theme_dark_onSurfaceVariant
//                    )
//                )
            } else {
                button.text = button.context.getString(R.string.track)
            }
        }

    }
}