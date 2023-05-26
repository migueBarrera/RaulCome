package com.mbmdevelop.raulcome

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class FeedInfo(
    @PrimaryKey(autoGenerate = true)
    val uid: Int,

    @ColumnInfo(name = "start_date")
    val startDate: Date?,

    @ColumnInfo(name = "end_date")
    var endDate: Date?,

    // 0 = null
    // 1 = left
    // 2 = right
    // 3 = both
    @ColumnInfo(name = "boobs")
    var boob: Int,
)