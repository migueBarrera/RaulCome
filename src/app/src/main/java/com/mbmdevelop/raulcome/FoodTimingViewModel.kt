package com.mbmdevelop.raulcome

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class FoodTimingViewModel @Inject constructor(
    private val feedInfoDAO: FeedInfoDAO)  : ViewModel() {

    val listFeedInfoObserver = MutableLiveData<List<FoodTiming>>()

    fun onStart() {
        val feedInfo = feedInfoDAO.getAll()

        val pattern = "D"
        val formatToGroup = SimpleDateFormat(pattern)

        val patternDay = "MM/dd"
        val formatToday = SimpleDateFormat(patternDay)

        var groupedList = feedInfo
            .groupBy { formatToGroup.format(it.startDate) }

        val foodTimingList = groupedList.map { (key, items) ->
            val totalTimeLong = items.sumOf{
                if (it.startDate == null || it.endDate == null){
                    0L
                }else{
                    calcularTiempoTranscurridoDuration(it.startDate, it.endDate!!)
                }
            }
            val dateString = formatToday.format(items.first().startDate!!)
            FoodTiming(dateString, totalTimeLong)
        }

        listFeedInfoObserver.value = foodTimingList
    }
}