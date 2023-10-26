package com.gb.tonometer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gb.tonometer.data.date_provider.DateAndTimeProvider
import com.gb.tonometer.domain.TonometerRepository
import javax.inject.Inject

class TonometerViewModelFactory @Inject constructor(
    private val repository: TonometerRepository,
    private val dateProvider: DateAndTimeProvider
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TonometerViewModelImpl(repository, dateProvider) as T
    }
}