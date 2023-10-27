package com.gb.tonometer.domain

import com.gb.tonometer.domain.entity.TonometerData
import kotlinx.coroutines.flow.SharedFlow

interface TonometerRepository {
    suspend fun getTonometerData(): SharedFlow<List<TonometerData>>

    suspend fun insertTonometerData(tonometerData: TonometerData)

    suspend fun deleteTonometerData(id: String)
}