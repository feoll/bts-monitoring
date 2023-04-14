package com.example.bts_monitoring.presentation.fragments.zonedetails.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.bts_monitoring.R
import com.example.bts_monitoring.databinding.CarBottomSheetBinding
import com.example.bts_monitoring.presentation.viewmodels.zonedetails.ZoneDetailsViewModel
import com.example.domain.models.Status
import com.example.domain.models.TypeCar
import com.example.domain.models.TypeQueue
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.ChipGroup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarBottomSheet : BottomSheetDialogFragment() {

    private var _binding: CarBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ZoneDetailsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CarBottomSheetBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupFilterListener()
    }

    private fun setupFilterListener() {
        val chipGroupListener = ChipGroup.OnCheckedStateChangeListener { _, _ ->
            when (binding.carTypeChipGroup.checkedChipId) {
                R.id.passenger_car_chip -> viewModel.typeCar.value = TypeCar.PASSENGER
                R.id.truck_chip -> viewModel.typeCar.value = TypeCar.TRUCK
                R.id.bus_chip -> viewModel.typeCar.value = TypeCar.BUS
                R.id.motorcycle_chip -> viewModel.typeCar.value = TypeCar.MOTORCYCLE
            }

            when (binding.typeQueueChipGroup.checkedChipId) {
                R.id.liveQueue_chip -> viewModel.typeQueue.value = TypeQueue.LIVE
                R.id.priorityQueue_chip -> viewModel.typeQueue.value = TypeQueue.PRIORITY
            }

            when (binding.statusChipGroup.checkedChipId) {
                R.id.status_called_chip -> viewModel.status.value = Status.CALLED
                R.id.status_cancelled_chip -> viewModel.status.value = Status.CANCELLED
                R.id.status_arrived_chip -> viewModel.status.value = Status.ARRIVED
                R.id.status_all_chip -> viewModel.status.value = Status.UNKNOWN
            }
        }

        binding.carTypeChipGroup.setOnCheckedStateChangeListener(chipGroupListener)
        binding.typeQueueChipGroup.setOnCheckedStateChangeListener(chipGroupListener)
        binding.statusChipGroup.setOnCheckedStateChangeListener(chipGroupListener)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}