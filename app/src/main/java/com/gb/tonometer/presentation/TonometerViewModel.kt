package com.gb.tonometer.presentation

import androidx.lifecycle.LiveData
import com.gb.tonometer.presentation.view_entity.ViewTonometerData

interface TonometerViewModel {
    fun getTonometerLiveData(): LiveData<List<ViewTonometerData>>

    fun getTonometerData()

    fun insertTonometerData(tonometerMeasurement: List<String>)

    fun deleteTonometerData(position: Int)
}