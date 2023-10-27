package com.gb.tonometer.presentation.recycle_view

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.gb.tonometer.databinding.ViewDateBinding

class DateViewHolder(private val binding: ViewDateBinding) :
    ViewHolder(binding.root) {
    fun bind(date: String) {
        binding.run {
            dateTextView.text = date
        }
    }
}