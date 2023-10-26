package com.gb.tonometer.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class TonometerData(
    val date: String,
    val time: String,
    val tonometerMeasurement: TonometerMeasurement
) : Parcelable
