package com.airwrk.androidtriviaquizgame

import android.app.Application

class TriviaQuizApp: Application() {

    lateinit var application:Application
    lateinit var contextReference:Application


    override fun onCreate() {
        super.onCreate()
        application = this
        contextReference = this
    }
}