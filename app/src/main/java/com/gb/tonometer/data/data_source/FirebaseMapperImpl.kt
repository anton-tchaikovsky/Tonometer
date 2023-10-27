package com.gb.tonometer.data.data_source

import com.gb.tonometer.domain.entity.TonometerData
import com.gb.tonometer.domain.entity.TonometerMeasurement
import javax.inject.Inject

class FirebaseMapperImpl @Inject constructor() : FirebaseMapper {
    override fun mapToFirebaseEntity(tonometerData: TonometerData): HashMap<String, Any> {
        val firebaseEntity = HashMap<String, Any>()
        firebaseEntity["date"] = tonometerData.date
        firebaseEntity["time"] = tonometerData.time
        firebaseEntity["systolic pressure"] = tonometerData.tonometerMeasurement.systolicPressure
        firebaseEntity["diastolic pressure"] = tonometerData.tonometerMeasurement.diastolicPressure
        firebaseEntity["pulse"] = tonometerData.tonometerMeasurement.pulse
        return firebaseEntity
    }

    override fun mapToDomainEntity(firebaseEntity: Map<String, Any>, id: String): TonometerData =
        TonometerData(
            id = id,
            date = firebaseEntity["date"].toString(),
            time = firebaseEntity["time"].toString(),
            TonometerMeasurement(
                systolicPressure = firebaseEntity["systolic pressure"].toString().toInt(),
                diastolicPressure = firebaseEntity["diastolic pressure"].toString().toInt(),
                firebaseEntity["pulse"].toString().toInt()
            )
        )
}