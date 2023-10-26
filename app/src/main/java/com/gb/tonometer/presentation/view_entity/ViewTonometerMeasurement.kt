package com.gb.tonometer.presentation.view_entity

import com.gb.tonometer.domain.entity.TonometerMeasurement

class ViewTonometerMeasurement(
    val time: String,
    val tonometerMeasurement: TonometerMeasurement,
    val isNorma: Boolean
)