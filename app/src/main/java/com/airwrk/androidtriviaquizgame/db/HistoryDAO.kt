package com.airwrk.androidtriviaquizgame.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.airwrk.androidtriviaquizgame.model.History

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(history : List<History>)

    @Query("SELECT * FROM History")
    suspend fun getHistory() : List<History>


}