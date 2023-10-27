package com.gb.tonometer.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TonometerMeasurement(
    val systolicPressure: Int = 0,
    val diastolicPressure: Int = 0,
    val pulse: Int = 0
): Parcelable
