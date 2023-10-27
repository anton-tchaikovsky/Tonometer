package com.gb.tonometer.presentation.view_entity

import com.gb.tonometer.domain.entity.TonometerData
import com.gb.tonometer.domain.entity.TonometerMeasurement

class ViewTonometerMeasurement(
    val id: String = TonometerData.DEFAULT_ID,
    val time: String,
    val tonometerMeasurement: TonometerMeasurement,
    val isNorma: Boolean
)