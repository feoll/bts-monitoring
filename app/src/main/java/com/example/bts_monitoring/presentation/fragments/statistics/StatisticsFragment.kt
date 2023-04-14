package com.example.bts_monitoring.presentation.fragments.statistics

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bts_monitoring.databinding.FragmentStatisticsBinding
import com.example.bts_monitoring.presentation.viewmodels.statistics.StatisticsViewModel
import com.example.domain.models.Zone
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        viewModel.getZones()

        viewModel.zones.observe(viewLifecycleOwner) {
            it.onSuccess { zones ->
                setDataToPieChart(zones)
            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPieChart()
    }

    private fun setupPieChart() = with(binding) {
        pieChart.description.text = ""
        pieChart.centerText = "Всего транспортных средств\n(cтатистика за день)"
        pieChart.setNoDataText("Нет данных")
        pieChart.legend.textColor = Color.WHITE
        pieChart.animateY(2000)
    }

    private fun getPieDataSet(list: List<PieEntry>): PieDataSet {
        val pieDataSet = PieDataSet(list, "")
        pieDataSet.setColors(*(ColorTemplate.COLORFUL_COLORS + ColorTemplate.getHoloBlue()))
        pieDataSet.valueTextColor = Color.WHITE
        pieDataSet.valueTextSize = 15f
        pieDataSet.setSliceSpace(3f)
        pieDataSet.setSelectionShift(5f)

        pieDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        }
        return pieDataSet
    }

    private fun setDataToPieChart(data: List<Zone>) = with(binding) {
        val list: ArrayList<PieEntry> = ArrayList()

        data.forEach {
            if (it.countAll != 0) {
                list.add(PieEntry(it.countAll.toFloat(), it.name))
            }
        }

        val pieDataSet = getPieDataSet(list)
        pieChart.data = PieData(pieDataSet)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}