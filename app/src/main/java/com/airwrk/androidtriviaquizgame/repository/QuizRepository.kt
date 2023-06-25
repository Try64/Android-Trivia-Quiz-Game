package com.airwrk.androidtriviaquizgame.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.airwrk.androidtriviaquizgame.db.QuizDB
import com.airwrk.androidtriviaquizgame.extentions.DomainConverters
import com.airwrk.androidtriviaquizgame.model.History
import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.retrofit.QuizAPI
import javax.inject.Inject


class QuizRepository @Inject constructor(private val quizAPI: QuizAPI, private val quizDB: QuizDB) {

    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>>
        get() = _questions

    private val _questionsWithID = MutableLiveData<List<Question>>()
    val questionsWithID: LiveData<List<Question>>
        get() = _questionsWithID

    private val _historyWithID = MutableLiveData<List<History>>()
    val historyWithID: LiveData<List<History>>
        get() = _historyWithID

    suspend fun getQuestions(){
        val result = quizAPI.getQuestions()
        if(result.isSuccessful && result.body() != null){
            quizDB.getQuestionsDAO().addQuestion(DomainConverters.getQuestionsFromBackend(response = result.body()!!))
            _questions.postValue(DomainConverters.getQuestionsFromBackend(response = result.body()!!))
        }
    }

    suspend fun updateQuestion(question:Question){
        quizDB.getQuestionsDAO().updateQuestion(question)
    }

    suspend fun getQuestionsFromDB(){
        _questionsWithID.postValue(quizDB.getQuestionsDAO().getQuestions())
    }

    suspend fun recordHistory(history: History){
        quizDB.getHistoryDAO().addHistory(history = history)
    }

    suspend fun getAllHistory(){
        _historyWithID.postValue(quizDB.getHistoryDAO().getHistory())
    }

}