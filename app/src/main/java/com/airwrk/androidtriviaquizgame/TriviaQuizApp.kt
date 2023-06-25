package com.airwrk.androidtriviaquizgame

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TriviaQuizApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set

        lateinit var instance: TriviaQuizApp
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = BuildConfig.PREFERENCE_KEY)
    }
}