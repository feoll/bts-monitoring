package com.example.bts_monitoring.presentation.fragments.statistics


import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.View
import androidx.fragment.app.viewModels
import com.example.bts_monitoring.R
import com.example.bts_monitoring.databinding.FragmentStatisticsBinding
import com.example.bts_monitoring.presentation.fragments.base.BaseFragment
import com.example.bts_monitoring.presentation.utils.color.ColorUtil
import com.example.bts_monitoring.presentation.viewmodels.statistics.StatisticsViewModel
import com.example.domain.models.Zone
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class StatisticsFragment : BaseFragment<FragmentStatisticsBinding>(R.layout.fragment_statistics) {

    private val viewModel: StatisticsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupPieChart()
        setupObservers()
  }

    private fun setupObservers() = with(viewModel) {
        zones.observe(viewLifecycleOwner) {
            it.onSuccess { zones ->
                showPieChart(zones)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getZones()
    }

    private fun setupPieChart() = with(binding) {
        pieChart.setNoDataText(getString(R.string.no_data))
        pieChart.description.text = ""
        val l: Legend = pieChart.getLegend()
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.textSize = 14f
        l.setDrawInside(false)
        l.textColor = ColorUtil.getColor(requireContext(), R.attr.legendColor)
        pieChart.animateY(2000, Easing.EaseInOutCubic);
        pieChart.setHoleColor(ColorUtil.getColor(requireContext(), R.attr.pieHoleColor))
        pieChart.setCenterText(generateCenterText())
        pieChart.setEntryLabelColor(ColorUtil.getColor(requireContext(), R.attr.pieValueTextColor))
    }


    private fun generateCenterText(): SpannableString {
        val span = SpannableString(getString(R.string.pieChartTitle))
        span.setSpan(ForegroundColorSpan(ColorUtil.getColor(requireContext(), R.attr.pieCenterTitleColor)), 0, 26, 0)
        span.setSpan(RelativeSizeSpan(2f), 0, 26, 0)
        span.setSpan(ForegroundColorSpan(ColorUtil.getColor(requireContext(), R.attr.pieCenterSubtitleColor)), 26, span.length, 0)
        return span
    }

    private fun showPieChart(zones: List<Zone>) {
        val list = ArrayList<PieEntry>()

        zones.forEach {
            if (it.countAll > 0) {
                list.add(PieEntry(it.countAll.toFloat(), it.name))
            }
        }

        binding.pieChart.data = PieData(getPieDataSet(list))
        binding.pieChart.invalidate()
    }

    private fun getPieDataSet(list: ArrayList<PieEntry>): PieDataSet {
        val pieDataSet = PieDataSet(list, "")
        val color = listOf(
            ColorUtil.getColor(requireContext(), R.attr.piePieceColor1),
            ColorUtil.getColor(requireContext(), R.attr.piePieceColor2),
            ColorUtil.getColor(requireContext(), R.attr.piePieceColor3),
            ColorUtil.getColor(requireContext(), R.attr.piePieceColor4),
            ColorUtil.getColor(requireContext(), R.attr.piePieceColor5),
            ColorUtil.getColor(requireContext(), R.attr.piePieceColor6)
        )
        pieDataSet.colors = color
        pieDataSet.valueTextColor = ColorUtil.getColor(requireContext(), R.attr.pieValueTextColor)
        pieDataSet.valueTextSize = 15f
        pieDataSet.selectionShift = 5f
        pieDataSet.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return value.toInt().toString()
            }
        }
        return pieDataSet
    }

}