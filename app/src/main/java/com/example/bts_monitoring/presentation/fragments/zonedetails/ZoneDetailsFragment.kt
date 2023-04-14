package com.example.bts_monitoring.presentation.fragments.zonedetails

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bts_monitoring.R
import com.example.bts_monitoring.common.safeNavigate
import com.example.bts_monitoring.databinding.FragmentZoneDetailsBinding
import com.example.bts_monitoring.presentation.utils.adapter.ZoneDetailsAdapter
import com.example.bts_monitoring.presentation.viewmodels.zonedetails.ZoneDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ZoneDetailsFragment : Fragment(), MenuProvider {

    private var _binding: FragmentZoneDetailsBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ZoneDetailsFragmentArgs>()
    private val adapter by lazy { ZoneDetailsAdapter() }
    private val viewModel: ZoneDetailsViewModel by activityViewModels()

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) =
        menuInflater.inflate(R.menu.zone_menu, menu)

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.menu_search -> setupSearchView(menuItem.actionView as SearchView)
            android.R.id.home -> findNavController().navigateUp()
        }
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZoneDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadQueueData()
        setupQueueRecyclerView()
        setupObserver()
        setupSortButtonListener()
        setupListener()
    }

    private fun loadQueueData() = viewModel.loadAllQueue(args.id)

    private fun setupListener() = with(binding) {
        swipeRefresh.setOnRefreshListener {
            loadQueueData()
        }
    }

    private fun setupSortButtonListener() = binding.sortButton.setOnClickListener {
        safeNavigate(ZoneDetailsFragmentDirections.actionZoneDetailsFragmentToCarBottomSheet())
    }

    private fun setupQueueRecyclerView() {
        binding.zoneRV.adapter = adapter
        binding.zoneRV.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun filterAdapterData(newText: String) {
        viewModel.carQueue.value?.onSuccess { list ->
            adapter.setData(list.filter {
                it.regnum.lowercase().startsWith(newText.lowercase())
            })
        }
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.maxWidth = Int.MAX_VALUE
        searchView.queryHint = getString(R.string.enter_car_number)

        val listener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterAdapterData(newText)
                return true
            }
        }

        searchView.setOnQueryTextListener(listener)
    }

    private fun setupObserver() {
        viewModel.allQueue.observe(viewLifecycleOwner) { queue ->
            queue?.fold(onSuccess = {
                viewModel.loadSortedCarQueue()
            }, onFailure = {
                Toast.makeText(context, getString(R.string.failed_to_load_data), Toast.LENGTH_SHORT)
                    .show()
            })
        }
        viewModel.carQueue.observe(viewLifecycleOwner) { queue ->
            queue?.onSuccess { list ->
                adapter.setData(list)
                binding.noData.visibility = if (list.isEmpty()) View.VISIBLE else View.INVISIBLE
                binding.swipeRefresh.isRefreshing = false
            }
        }
        viewModel.typeQueue.observe(viewLifecycleOwner) {
            viewModel.loadSortedCarQueue()
        }
        viewModel.status.observe(viewLifecycleOwner) {
            viewModel.loadSortedCarQueue()
        }
        viewModel.typeCar.observe(viewLifecycleOwner) {
            viewModel.loadSortedCarQueue()
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.clearQueues()
    }
}