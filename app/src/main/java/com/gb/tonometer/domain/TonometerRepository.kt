package com.gb.tonometer.domain

import com.gb.tonometer.domain.entity.TonometerData

interface TonometerRepository {
    suspend fun getTonometerData(): List<TonometerData>

    suspend fun insertTonometerData(tonometerData: TonometerData)

    suspend fun deleteTonometerData(tonometerData: TonometerData)
}