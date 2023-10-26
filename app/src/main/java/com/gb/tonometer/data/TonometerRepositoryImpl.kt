package com.gb.tonometer.data

import com.gb.tonometer.domain.TonometerRepository
import com.gb.tonometer.domain.entity.TonometerData
import com.gb.tonometer.domain.entity.TonometerMeasurement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TonometerRepositoryImpl @Inject constructor(): TonometerRepository {

    private val tonometerData = mutableListOf(TonometerData("14 October", "07:09", TonometerMeasurement(125, 82, 63)),
        TonometerData("24 October", "12:07", TonometerMeasurement(105, 80, 61)))


    override suspend fun getTonometerData(): List<TonometerData> =
        withContext(Dispatchers.IO){
            tonometerData
        }

    override suspend fun insertTonometerData(tonometerData: TonometerData) {
        withContext(Dispatchers.IO){
            this@TonometerRepositoryImpl.tonometerData.add(tonometerData)
        }

    }

    override suspend fun deleteTonometerData(tonometerData: TonometerData) {
        withContext(Dispatchers.IO){
            this@TonometerRepositoryImpl.tonometerData.remove(tonometerData)
        }
    }
}