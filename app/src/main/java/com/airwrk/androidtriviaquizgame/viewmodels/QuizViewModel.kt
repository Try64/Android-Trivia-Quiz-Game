package com.airwrk.androidtriviaquizgame.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.repository.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(private val repository: QuizRepository) : ViewModel() {

    val questionsLiveData : LiveData<List<Question>>
        get() = repository.questions

    val questionsLiveDataWithID : LiveData<List<Question>>
        get() = repository.questionsWithID

    init {
        viewModelScope.launch {
            repository.getQuestions()
        }
    }

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



//    fun addDataToDB(list:List<Question>){
//        repository.
//    }

}