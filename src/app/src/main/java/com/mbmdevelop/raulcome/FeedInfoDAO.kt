package com.mbmdevelop.raulcome

import androidx.room.*

@Dao
interface FeedInfoDAO {
    @Query("SELECT * FROM feedInfo")
    fun getAll(): List<FeedInfo>

    @Insert
    fun insertAll(vararg feedInfos: FeedInfo)

    @Update
    fun update(vararg feedInfos: FeedInfo)

    @Delete
    fun delete(feedInfo: FeedInfo)

    @Query("SELECT * FROM feedInfo ORDER BY uid DESC LIMIT 1")
    fun getLast(): FeedInfo?
}