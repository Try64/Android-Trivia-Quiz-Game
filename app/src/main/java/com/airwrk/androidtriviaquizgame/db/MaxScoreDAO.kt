package com.airwrk.androidtriviaquizgame.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.airwrk.androidtriviaquizgame.model.MaxScore

@Dao
interface MaxScoreDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMaxScore(maxScore : MaxScore)

    @Update()
    suspend fun updateMaxScore(maxScore : MaxScore)

    @Query("SELECT * FROM MaxScore")
    suspend fun getMaxScore() : List<MaxScore>

    @Query("DELETE FROM MaxScore")
    suspend fun deleteMax()
}