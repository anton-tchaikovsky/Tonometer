package com.gb.tonometer.data.date_provider

interface DateAndTimeProvider {
    fun getTodayDate(): String
    fun getCurrentTime(): String
}