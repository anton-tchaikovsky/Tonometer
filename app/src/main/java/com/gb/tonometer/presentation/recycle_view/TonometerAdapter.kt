package com.gb.tonometer.presentation.recycle_view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gb.tonometer.databinding.ViewDateBinding
import com.gb.tonometer.databinding.ViewTomometerMeasurementBinding
import com.gb.tonometer.presentation.view_entity.ViewTonometerData

class TonometerAdapter (private val removeMeasurementClickListener: (Int) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {

    private var tonometerDataList: List<ViewTonometerData> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            DATE -> DateViewHolder(
                ViewDateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

            )

            MEASUREMENT -> TonometerMeasurementViewHolder(
                ViewTomometerMeasurementBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalStateException()
        }


    override fun getItemCount(): Int =
        tonometerDataList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateViewHolder -> holder.bind(tonometerDataList[position].date)
            is TonometerMeasurementViewHolder -> holder.bind(
                tonometerDataList[position].tonometerMeasurement ?: throw IllegalStateException()
            )
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (tonometerDataList[position].tonometerMeasurement == null)
            DATE
        else
            MEASUREMENT

    @SuppressLint("NotifyDataSetChanged")
    fun setTonometerData(tonometerDataList: List<ViewTonometerData>) {
        this.tonometerDataList = tonometerDataList
        notifyDataSetChanged()
    }

    companion object {
        private const val DATE = 1
        private const val MEASUREMENT = 2
    }

    override fun onRemoveItem(position: Int) {
        removeMeasurementClickListener(position)
    }
}