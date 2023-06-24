package com.airwrk.androidtriviaquizgame.retrofit

import com.airwrk.androidtriviaquizgame.model.ResponseQuestions
import retrofit2.Response
import retrofit2.http.GET

interface QuizAPI {
    @GET("questions")
    suspend fun getQuestions() : Response<ResponseQuestions>
}