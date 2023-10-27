package com.gb.tonometer.data

import com.gb.tonometer.data.data_source.FirebaseDataSource
import com.gb.tonometer.domain.TonometerRepository
import com.gb.tonometer.domain.entity.TonometerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TonometerRepositoryImpl @Inject constructor(private val dataSource: FirebaseDataSource) :
    TonometerRepository {
    override suspend fun getTonometerData(): SharedFlow<List<TonometerData>> =
        withContext(Dispatchers.IO) { dataSource.getTonometerData() }

    override suspend fun insertTonometerData(tonometerData: TonometerData) {
        withContext(Dispatchers.IO) { dataSource.insertTonometerData(tonometerData) }
    }

    override suspend fun deleteTonometerData(id: String) {
        withContext(Dispatchers.IO) { dataSource.deleteTonometerData(id) }
    }
}