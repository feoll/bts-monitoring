package com.example.bts_monitoring.presentation.fragments.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bts_monitoring.R
import com.example.bts_monitoring.databinding.FragmentSettingsBinding
import com.example.bts_monitoring.presentation.activities.MainActivity
import com.example.bts_monitoring.presentation.viewmodels.settings.SettingsViewModel
import com.example.domain.models.TypeAppTheme
import com.example.domain.models.TypeCar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()
    private val carArray by lazy { resources.getStringArray(R.array.carNames) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadProfileData()
        setupObservers()
        setupUIListenerForSaveData()
    }

    override fun onResume() {
        super.onResume()
        setupAutoCompleteTextAdapter()
    }

    private fun setupUIListenerForSaveData() {
        binding.numberCarInputLayout.editText?.doOnTextChanged{ inputText, _, _, _ ->
            viewModel.saveRegNumber(inputText.toString().uppercase())
        }

        binding.carAutoCompleteText.setOnItemClickListener { _, _, position: Int, _ ->
            when (position) {
                0 -> viewModel.saveCarType(type = TypeCar.PASSENGER)
                1 -> viewModel.saveCarType(type = TypeCar.TRUCK)
                2 -> viewModel.saveCarType(type = TypeCar.BUS)
                3 -> viewModel.saveCarType(type = TypeCar.MOTORCYCLE)
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                viewModel.saveTypeAppTheme(TypeAppTheme.DARK)
                (activity as MainActivity).setDarkThemeApp(true)
            } else {
                viewModel.saveTypeAppTheme(TypeAppTheme.LIGHT)
                (activity as MainActivity).setDarkThemeApp(false)
            }
        }
    }

    private fun setupObservers() {
        viewModel.regNumber.observe(viewLifecycleOwner) {
            binding.numberCarInputLayout.editText?.setText(it)
        }

        viewModel.carType.observe(viewLifecycleOwner) { type ->
            when (type) {
                TypeCar.PASSENGER -> binding.carAutoCompleteText.setText(carArray[0], false)
                TypeCar.TRUCK -> binding.carAutoCompleteText.setText(carArray[1], false)
                TypeCar.BUS -> binding.carAutoCompleteText.setText(carArray[2], false)
                TypeCar.MOTORCYCLE -> binding.carAutoCompleteText.setText(carArray[3], false)
                else -> {}
            }
        }

        viewModel.appTheme.observe(viewLifecycleOwner) { typeTheme ->
            binding.switchTheme.isChecked = typeTheme == TypeAppTheme.DARK
        }
    }

    private fun setupAutoCompleteTextAdapter() {
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, carArray)
        binding.carAutoCompleteText.setAdapter(arrayAdapter)
    }

    private fun loadProfileData() {
        viewModel.loadRegNumber()
        viewModel.loadCarType()
        viewModel.loadAppTheme()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
