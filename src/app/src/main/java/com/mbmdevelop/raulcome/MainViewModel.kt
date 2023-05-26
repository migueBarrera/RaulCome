package com.mbmdevelop.raulcome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val feedInfoDAO: FeedInfoDAO,
    private val alarmService: AlarmService)  : ViewModel() {

    val listFeedInfoObserver = MutableLiveData<List<FeedInfo>>()

    fun openAlarmApp():Boolean{
        return alarmService.openAlarmApp()
    }

    fun onStart() {
        listFeedInfoObserver.value = feedInfoDAO.getAll()
    }

    fun hasAPendingFeedInfoForClose(): Boolean {
        val lastItem = feedInfoDAO.getLast()
        return lastItem?.endDate == null
    }

    fun openFeedInfo(time: Date) {
        val feed = FeedInfo(0, time, null, 0)
        feedInfoDAO.insertAll(feed)
        listFeedInfoObserver.value = feedInfoDAO.getAll()
    }

    fun closeLastFeedInfo(date: Date, boob: Int) {
        val feedInfo = feedInfoDAO.getLast()
        feedInfo?.let {
            it.endDate = date
            it.boob = boob
            feedInfoDAO.update(it)
            listFeedInfoObserver.value = feedInfoDAO.getAll()
        }
    }
}