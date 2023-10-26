package com.gb.tonometer.presentation.recycle_view

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val adapter: TonometerAdapter) :
    ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val swipeFlags = if (viewHolder is TonometerMeasurementViewHolder)
            ItemTouchHelper.START or ItemTouchHelper.END
        else
            0
        return makeMovementFlags(
            0,
            swipeFlags
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapter.onRemoveItem(viewHolder.adapterPosition)
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }
}