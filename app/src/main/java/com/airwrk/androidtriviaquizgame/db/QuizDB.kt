package com.airwrk.androidtriviaquizgame.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.airwrk.androidtriviaquizgame.model.History
import com.airwrk.androidtriviaquizgame.model.MaxScore
import com.airwrk.androidtriviaquizgame.model.Question

@Database(entities = [Question::class,MaxScore::class,History::class], version = 1)
abstract class QuizDB : RoomDatabase() {

    abstract fun getHistoryDAO() : HistoryDAO
    abstract fun getMaxScoreDAO() : MaxScoreDAO
    abstract fun getQuestionsDAO() : QuestionsDAO

}