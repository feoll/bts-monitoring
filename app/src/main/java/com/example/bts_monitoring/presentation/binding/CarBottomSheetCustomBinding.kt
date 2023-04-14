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

        /**
         *  Набор привязок, которые нужны для сохранения выбранного чипа во viewModel
         */
//        @BindingAdapter(value = ["viewModel", "typeCar"], requireAll = true)
//        @JvmStatic
//        fun saveChipStateToViewModel(
//            chip: Chip,
//            viewModel: ZoneDetailsViewModel,
//            typeCar: TypeCar
//        ) {
//            (chip.parent as ChipGroup).setOnCheckedStateChangeListener(ChipGroup.OnCheckedStateChangeListener { _, _ ->
//                viewModel.typeCar.value = typeCar
//
//                Log.d("хцй", viewModel.typeCar.value?.name.toString())
//                Log.d("хцй", viewModel.typeQueue.value?.name.toString())
//                Log.d("хцй", viewModel.status.value?.name.toString())
//
//                viewModel.loadSortedCarQueue()
//            })
//        }

//        @BindingAdapter(value = ["viewModel", "typeQueue"], requireAll = true)
//        @JvmStatic
//        fun saveChipStateToViewModel(
//            chip: Chip,
//            viewModel: ZoneDetailsViewModel,
//            typeQueue: TypeQueue
//        ) {
//            (chip.parent as ChipGroup).setOnCheckedStateChangeListener(ChipGroup.OnCheckedStateChangeListener { _, _ ->
//                viewModel.typeQueue.value = typeQueue
//
//                Log.d("хцй", viewModel.typeCar.value?.name.toString())
//                Log.d("хцй", viewModel.typeQueue.value?.name.toString())
//                Log.d("хцй", viewModel.status.value?.name.toString())
//
//                viewModel.loadSortedCarQueue()
//            })
//        }
//
//        @BindingAdapter(value = ["viewModel", "status"], requireAll = true)
//        @JvmStatic
//        fun saveChipStateToViewModel(
//            chip: Chip,
//            viewModel: ZoneDetailsViewModel,
//            status: Status
//        ) {
//            (chip.parent as ChipGroup).setOnCheckedStateChangeListener(ChipGroup.OnCheckedStateChangeListener { _, _ ->
//                viewModel.status.value = status
//
//                Log.d("хцй", viewModel.typeCar.value?.name.toString())
//                Log.d("хцй", viewModel.typeQueue.value?.name.toString())
//                Log.d("хцй", viewModel.status.value?.name.toString())
//
//                viewModel.loadSortedCarQueue()
//            })
//        }

    }
}