package com.gb.tonometer.presentation.recycle_view

import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gb.tonometer.R
import com.gb.tonometer.databinding.ViewTomometerMeasurementBinding
import com.gb.tonometer.presentation.view_entity.ViewTonometerMeasurement

class TonometerMeasurementViewHolder(private val binding: ViewTomometerMeasurementBinding) :
    ViewHolder(binding.root) {
    fun bind(tonometerMeasurement: ViewTonometerMeasurement) {
        binding.run {
            tonometerMeasurement.let {
                timeTextView.text = it.time
                systolicPressureTextView.text = it.tonometerMeasurement.systolicPressure.toString()
                diastolicPressureTextView.text =
                    it.tonometerMeasurement.diastolicPressure.toString()
                pulseTextView.text = it.tonometerMeasurement.pulse.toString()
                if (!it.isNorma) {
                    diastolicPressureLayout.background = AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.gradient_orange_white
                    )
                    systolicPressureLayout.background = AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.gradient_white_orange
                    )
                } else {
                    diastolicPressureLayout.background = AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.gradient_green_white
                    )
                    systolicPressureLayout.background = AppCompatResources.getDrawable(
                        itemView.context,
                        R.drawable.gradient_white_green
                    )
                }
            }
        }
    }
}