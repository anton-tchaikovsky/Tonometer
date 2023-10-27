package com.gb.tonometer.data.data_source

import com.gb.tonometer.domain.entity.TonometerData
import kotlinx.coroutines.flow.SharedFlow

interface FirebaseDataSource {
    suspend fun getTonometerData(): SharedFlow<List<TonometerData>>

    suspend fun insertTonometerData(tonometerData: TonometerData)

    suspend fun deleteTonometerData(id: String)

}