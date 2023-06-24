package com.airwrk.androidtriviaquizgame.model

class ResponseQuestions : ArrayList<ResponseQuestions.ResponseQuestionsItem>(){
    data class ResponseQuestionsItem(
        val category: String,
        val correctAnswer: String,
        val difficulty: String,
        val id: String,
        val incorrectAnswers: List<String>,
        val isNiche: Boolean,
        val question: Question,
        val regions: List<Any>,
        val tags: List<String>,
        val type: String
    ) {
        data class Question(
            val text: String
        )
    }
}