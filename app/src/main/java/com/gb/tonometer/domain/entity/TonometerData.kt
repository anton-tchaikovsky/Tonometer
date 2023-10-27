package com.gb.tonometer.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class TonometerData(
    val id: String = DEFAULT_ID,
    val date: String,
    val time: String,
    val tonometerMeasurement: TonometerMeasurement
) : Parcelable{
    companion object{
        const val DEFAULT_ID = ""
    }
}
