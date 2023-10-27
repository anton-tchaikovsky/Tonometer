package com.gb.tonometer.di.modules

import com.gb.tonometer.data.TonometerRepositoryImpl
import com.gb.tonometer.data.data_source.FirebaseDataSource
import com.gb.tonometer.data.data_source.FirebaseDataSourceImpl
import com.gb.tonometer.data.data_source.FirebaseMapper
import com.gb.tonometer.data.data_source.FirebaseMapperImpl
import com.gb.tonometer.data.date_provider.DateAndTimeProvider
import com.gb.tonometer.data.date_provider.DateAndTimeProviderImpl
import com.gb.tonometer.data.preferences.TonometerSharedPreferences
import com.gb.tonometer.data.preferences.TonometerSharedPreferencesImpl
import com.gb.tonometer.domain.TonometerRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
    @Singleton
    @Binds
    fun tonometerRepository(tonometerRepository: TonometerRepositoryImpl): TonometerRepository

    @Singleton
    @Binds
    fun fireBaseDataSource(fireBaseDataSource: FirebaseDataSourceImpl): FirebaseDataSource

    @Singleton
    @Binds
    fun fireBaseMapper(fireBaseMapper: FirebaseMapperImpl): FirebaseMapper

    @Singleton
    @Binds
    fun tonometerSharedPreferences(tonometerSharedPreferences: TonometerSharedPreferencesImpl): TonometerSharedPreferences

    @Singleton
    @Binds
    fun dateProvider(dateAndTimeProvider: DateAndTimeProviderImpl): DateAndTimeProvider

}