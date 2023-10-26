package com.gb.tonometer.di.modules

import com.gb.tonometer.data.date_provider.DateAndTimeProvider
import com.gb.tonometer.domain.TonometerRepository
import com.gb.tonometer.presentation.TonometerViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun tonometerViewModelFactory(
        repository: TonometerRepository,
        dateProvider: DateAndTimeProvider
    ): TonometerViewModelFactory = TonometerViewModelFactory(repository, dateProvider)

}