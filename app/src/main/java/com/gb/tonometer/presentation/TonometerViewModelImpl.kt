package com.gb.tonometer.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gb.tonometer.data.date_provider.DateAndTimeProvider
import com.gb.tonometer.domain.TonometerRepository
import com.gb.tonometer.domain.entity.TonometerData
import com.gb.tonometer.domain.entity.TonometerMeasurement
import com.gb.tonometer.presentation.view_entity.ViewTonometerData
import com.gb.tonometer.presentation.view_entity.ViewTonometerMeasurement
import kotlinx.coroutines.launch
import javax.inject.Inject

class TonometerViewModelImpl @Inject constructor(
    private val repository: TonometerRepository,
    private val dateProvider: DateAndTimeProvider
) :
    ViewModel(), TonometerViewModel {

    private val viewEntities: MutableList<ViewTonometerData> = mutableListOf()

    private val tonometerLiveData: MutableLiveData<List<ViewTonometerData>> = MutableLiveData()

    override fun getTonometerLiveData(): LiveData<List<ViewTonometerData>> = tonometerLiveData

    override fun getTonometerData() {
        viewModelScope.launch {
            repository.getTonometerData().collect {
                viewEntities.clear()
                tonometerLiveData.value = mapToViewEntities(it)
            }
        }
    }

    override fun insertTonometerData(tonometerMeasurement: List<String>) {
        if (tonometerMeasurement.size != 3)
            throw IllegalStateException()
        val tonometerData = TonometerData(
            date = dateProvider.getTodayDate(),
            time = dateProvider.getCurrentTime(),
            tonometerMeasurement = TonometerMeasurement(
                checkTonometerMeasurement(tonometerMeasurement[0]),
                checkTonometerMeasurement(tonometerMeasurement[1]),
                checkTonometerMeasurement(tonometerMeasurement[2])
            )
        )
        addViewEntity(tonometerData)
        viewModelScope.launch {
            repository.insertTonometerData(
                tonometerData
            )
        }
    }

    override fun deleteTonometerData(id: String) {
        val viewTonometerData = viewEntities.find {
            it.tonometerMeasurement?.id == id
        }
        viewEntities.indexOf(viewTonometerData).let { position ->
            if (position == -1)
                throw java.lang.IllegalStateException()
            viewEntities.removeAt(position)
            if (isLastMeasurementWithThisDate(position))
                viewEntities.removeAt(position - 1)
            viewModelScope.launch {
                repository.deleteTonometerData(id)
            }
        }
    }

    private fun isLastMeasurementWithThisDate(position: Int): Boolean {
        return viewEntities[position - 1].tonometerMeasurement == null && (viewEntities.size == position || viewEntities[position].tonometerMeasurement == null)
    }

    private fun checkTonometerMeasurement(measurement: String): Int {
        return try {
            measurement.toInt()
        } catch (_: NumberFormatException) {
            0
        }
    }

    private fun addViewEntity(entity: TonometerData) {
        entity.let {
            if (viewEntities.isEmpty() || viewEntities.last().date != it.date)
                viewEntities.add(ViewTonometerData(it.date, null))
            viewEntities.add(
                ViewTonometerData(
                    it.date,
                    ViewTonometerMeasurement(
                        time = it.time,
                        tonometerMeasurement = it.tonometerMeasurement,
                        isNorma = isNorma(it.tonometerMeasurement)
                    )
                )
            )
        }
    }

    private fun mapToViewEntities(entities: List<TonometerData>): List<ViewTonometerData> {
        entities.forEachIndexed { index, it ->
            if (index == 0 || it.date != entities[index - 1].date)
                viewEntities.add(ViewTonometerData(it.date, null))
            viewEntities.add(
                ViewTonometerData(
                    it.date,
                    ViewTonometerMeasurement(
                        it.id,
                        it.time,
                        it.tonometerMeasurement,
                        isNorma(it.tonometerMeasurement)
                    )
                )
            )
        }
        return viewEntities.toList()
    }

    private fun isNorma(tonometerMeasurement: TonometerMeasurement): Boolean =
        tonometerMeasurement.let {
            !(it.systolicPressure > MAX_NORM_SYSTOLIC_PRESSURE || it.systolicPressure < MIN_NORM_SYSTOLIC_PRESSURE || it.diastolicPressure > MAX_NORM_DIASTOLIC_PRESSURE || it.diastolicPressure < MIN_NORM_DIASTOLIC_PRESSURE)
        }

    companion object {
        private const val MIN_NORM_SYSTOLIC_PRESSURE = 110
        private const val MAX_NORM_SYSTOLIC_PRESSURE = 130
        private const val MIN_NORM_DIASTOLIC_PRESSURE = 75
        private const val MAX_NORM_DIASTOLIC_PRESSURE = 85
    }
}
