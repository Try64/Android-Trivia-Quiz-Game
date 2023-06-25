package com.airwrk.androidtriviaquizgame.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airwrk.androidtriviaquizgame.model.History
import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.repository.QuizRepository
import com.airwrk.androidtriviaquizgame.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repository: QuizRepository) : ViewModel() {

    val questionsLiveData : LiveData<List<Question>>
        get() = repository.questions

    val questionsLiveDataWithID : LiveData<List<Question>>
        get() = repository.questionsWithID
    var _maxScore = MutableLiveData<Int>()
    val maxScoreLD : LiveData<Int>
        get() = _maxScore

    val historyLiveData : LiveData<List<History>>
        get() = repository.historyWithID


    fun updateQuestion(question: Question){
        viewModelScope.launch {
            repository.updateQuestion(question)
        }
    }

    fun getAllQuestionsFromDB(){
        viewModelScope.launch {
            repository.getQuestionsFromDB()
        }
    }

    fun updateMaxScore(context: Context, score:Int){
        viewModelScope.launch {
            runBlocking {
                val maxScoreTillNow = Constants.getMaxScore(context)
                if(score > maxScoreTillNow){
                    Constants.setMaxScore(context,score)
                }
            }
        }
    }

    fun getMaxScore(context: Context){
        viewModelScope.launch {
            runBlocking {
                val maxScoreTillNow = Constants.getMaxScore(context)
                _maxScore.postValue(maxScoreTillNow)
            }
        }
    }

    fun downLoadQuestions(){
        viewModelScope.launch {
            repository.getQuestions()
        }
    }

    fun recordHistory(history: History){
        viewModelScope.launch {
            repository.recordHistory(history)
        }
    }

    fun getAllHistory(){
        viewModelScope.launch {
            repository.getAllHistory()
        }
    }



//    fun addDataToDB(list:List<Question>){
//        repository.
//    }

}