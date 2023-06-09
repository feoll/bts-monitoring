package com.example.bts_monitoring.presentation.binding

import androidx.databinding.BindingAdapter
import com.example.bts_monitoring.presentation.viewmodels.zonedetails.ZoneDetailsViewModel
import com.example.domain.models.Status
import com.example.domain.models.TypeCar
import com.example.domain.models.TypeQueue
import com.google.android.material.chip.Chip

class CarBottomSheetCustomBinding {
    companion object {
        /**
         *  Набор привязок, которые нужны для сохранения состояния выбранного чипа в интерфейсе
         */

        @BindingAdapter(value = ["viewModel", "typeCar"], requireAll = true)
        @JvmStatic
        fun checkedTypeCar(chip: Chip, viewModel: ZoneDetailsViewModel, typeCar: TypeCar) {
            chip.isChecked = (viewModel.typeCar.value == typeCar)
        }

        @BindingAdapter(value = ["viewModel", "typeQueue"], requireAll = true)
        @JvmStatic
        fun checkedTypeQueue(chip: Chip, viewModel: ZoneDetailsViewModel, typeQueue: TypeQueue) {
            chip.isChecked = (viewModel.typeQueue.value == typeQueue)
        }

        @BindingAdapter(value = ["viewModel", "status"], requireAll = true)
        @JvmStatic
        fun checkedStatus(chip: Chip, viewModel: ZoneDetailsViewModel, status: Status) {
            chip.isChecked = (viewModel.status.value == status)
        }
    }
}