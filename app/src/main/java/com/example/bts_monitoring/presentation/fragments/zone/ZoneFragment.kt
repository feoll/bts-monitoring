package com.example.bts_monitoring.presentation.fragments.zone

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bts_monitoring.databinding.FragmentZoneBinding
import com.example.bts_monitoring.presentation.service.MonitoringService
import com.example.bts_monitoring.presentation.utils.adapter.ZoneAdapter
import com.example.bts_monitoring.presentation.viewmodels.zone.ZoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ClassCastException

@AndroidEntryPoint
class ZoneFragment : Fragment() {

    private var _binding: FragmentZoneBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ZoneViewModel by viewModels()
    private val adapter by lazy { ZoneAdapter(viewModel::saveAndLoadObserverZoneId) }
    private lateinit var monitoringService: MonitoringService

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            monitoringService = context as MonitoringService
        } catch (ex: ClassCastException) {
            Log.d("ZoneFragment", ex.message.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoneBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        showShimmerEffect()
        setupZoneRecyclerView()
        setupListener()
        setupObserver()
    }

    private fun setupListener() = with(binding) {
        swipeRefresh.setOnRefreshListener {
            viewModel.loadZones()
        }
    }

    private fun setupObserver() = with(viewModel) {
        zones.observe(viewLifecycleOwner) { result ->
            result.onSuccess { list ->
                list.forEach { zone ->
                    zone.phone = formatPhoneNumberForUI(zone.phone)
                }
                adapter.setData(list)
                hideShimmerEffect()
            }
            binding.swipeRefresh.isRefreshing = false
        }

        observerZoneId.observe(viewLifecycleOwner) { id ->
            adapter.setObserverZoneId(id = id)

            if (id.isEmpty()) {
                monitoringService.stopService()
            } else {
                monitoringService.startService()
            }
        }
    }

    private fun formatPhoneNumberForUI(phone: String): String = phone.run {
        var number = this
        number = number.replace(", ", "\n")
        number = number.replace(",", "\n")
        return@run number
    }


    private fun hideShimmerEffect() {
        binding.shimmerViewContainer.stopShimmer()
        binding.shimmerViewContainer.visibility = View.GONE
    }

    private fun showShimmerEffect() {
        binding.shimmerViewContainer.startShimmer()
        binding.shimmerViewContainer.visibility = View.VISIBLE
    }

    private fun setupZoneRecyclerView() {
        binding.areasRecyclerView.adapter = adapter
        binding.areasRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}