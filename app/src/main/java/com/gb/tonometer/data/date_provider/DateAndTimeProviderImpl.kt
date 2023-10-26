package com.gb.tonometer.data.date_provider

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateAndTimeProviderImpl @Inject constructor() : DateAndTimeProvider {
    override fun getTodayDate(): String =
        SimpleDateFormat("dd MMMM", Locale.ENGLISH).format(Date())


    override fun getCurrentTime(): String =
        SimpleDateFormat("HH:mm", Locale.ENGLISH).format(Date())

}