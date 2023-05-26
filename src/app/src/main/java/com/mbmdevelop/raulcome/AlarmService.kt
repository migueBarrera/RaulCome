package com.mbmdevelop.raulcome

import android.content.Context
import android.content.Intent
import android.provider.AlarmClock
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmService @Inject constructor(@ApplicationContext val context: Context) {

    fun openAlarmApp() : Boolean{
        val mClockIntent = Intent(AlarmClock.ACTION_SHOW_ALARMS)
        mClockIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(mClockIntent)
        return true
    }
}