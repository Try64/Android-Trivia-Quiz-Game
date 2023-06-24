package com.airwrk.androidtriviaquizgame.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.airwrk.androidtriviaquizgame.model.Question

@Dao
interface QuestionsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuestion(questions : List<Question>)

    @Query("DELETE FROM Question")
    suspend fun deleteQuestion()

    @Query("SELECT * FROM Question WHERE Question.isDisplayed = 'no'")
    suspend fun getUnAttendedQuestion():List<Question>

    @Update()
    suspend fun updateQuestion(question:Question)


}