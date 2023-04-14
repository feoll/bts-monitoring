package com.example.bts_monitoring.presentation.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bts_monitoring.R
import com.example.bts_monitoring.databinding.RowZonesBinding
import com.example.domain.models.Zone

class ZoneAdapter(private val saveObserverId: (String) -> Unit) :
    RecyclerView.Adapter<ZoneAdapter.ViewHolder>() {

    private var list: List<Zone> = emptyList()
    private var observerZoneId: String = ""

    class ViewHolder(private val binding: RowZonesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(zone: Zone, observerZoneId: String, saveObserverId: (String) -> Unit) {
            binding.zone = zone
            binding.observerZoneId = observerZoneId
            binding.executePendingBindings()

            binding.observerBtn.setOnClickListener {
                if (binding.observerBtn.text == binding.observerBtn.context.getString(R.string.tracking)) {
                    saveObserverId("")
                } else {
                    saveObserverId(zone.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowZonesBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun setData(zones: List<Zone>) {
        val zoneDiffUtil = ZoneDiffUtil(list, zones)
        val DiffUtilResult = DiffUtil.calculateDiff(zoneDiffUtil)
        this.list = zones
        DiffUtilResult.dispatchUpdatesTo(this)
    }

    fun setObserverZoneId(id: String) {
        this.observerZoneId = id
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], observerZoneId, saveObserverId)
    }

    override fun getItemCount(): Int = list.size
}

class ZoneDiffUtil(private val oldList: List<Zone>, private val newList: List<Zone>) :
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