package com.airwrk.androidtriviaquizgame.extentions

import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.model.ResponseQuestions

object DomainConverters {
    fun getQuestionsFromBackend(response:ResponseQuestions):List<Question>{
        val list = ArrayList<Question>()
        response.forEach{
            val ans = (1..4).random()
            var index = 0
            val options = ArrayList<String>()
            for(i in 1..4){
                if(i == ans){
                    options.add(it.correctAnswer)
                }else{
                    options.add(it.incorrectAnswers.get(index))
                    index++
                }
            }
            list.add(Question(ans = it.correctAnswer,question = it.question.text, option1 = options[0], option2 = options[1], option3 = options[2], option4 = options[3], isDisplayed = "no", id = null))
        }
        return list.toList()
    }
}