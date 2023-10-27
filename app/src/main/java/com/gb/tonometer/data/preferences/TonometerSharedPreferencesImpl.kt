package com.gb.tonometer.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class TonometerSharedPreferencesImpl @Inject constructor(context: Context) : TonometerSharedPreferences{

    private val sharedPreferencesForGame: SharedPreferences = context.getSharedPreferences(
        NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE
    )

    override fun isFirstLaunch(): Boolean {
        val isFirstLaunch = sharedPreferencesForGame.getBoolean(KEY_FIRST_LAUNCH, true)
        if (isFirstLaunch)
            sharedPreferencesForGame.edit()
                .putBoolean(KEY_FIRST_LAUNCH, false)
                .apply()
        return isFirstLaunch
    }

    companion object {
        private const val KEY_FIRST_LAUNCH = "KeyFirstLaunch"
        private const val NAME_SHARED_PREFERENCES = "tonometer_pref"
    }
}