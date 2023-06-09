package com.example.bts_monitoring.presentation.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bts_monitoring.databinding.RowZoneDetailsBinding
import com.example.domain.models.Car
import com.example.domain.models.TypeCar

class ZoneDetailsAdapter(private val saveRegNumberAndCarType: (String) -> Unit) : RecyclerView.Adapter<ZoneDetailsAdapter.ViewHolder>() {

    private var list: List<Car> = emptyList()

    class ViewHolder(private val binding: RowZoneDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(carInfo: Car, saveRegNumberAndCarType: (String) -> Unit) {
            binding.carInfo = carInfo
            binding.executePendingBindings()
            binding.card.setOnClickListener{
                saveRegNumberAndCarType(carInfo.regnum)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowZoneDetailsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setData(carInfo: List<Car>) {
        val bankCardDiffUtil = CarInfoDiffUtil(list, carInfo)
        val DiffUtilResult = DiffUtil.calculateDiff(bankCardDiffUtil)
        this.list = carInfo
        DiffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], saveRegNumberAndCarType)
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}

class CarInfoDiffUtil(private val oldList: List<Car>, private val newList: List<Car>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}