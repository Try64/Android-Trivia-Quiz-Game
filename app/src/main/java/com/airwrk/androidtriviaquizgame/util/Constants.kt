package com.airwrk.androidtriviaquizgame.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.airwrk.androidtriviaquizgame.BuildConfig
import com.airwrk.androidtriviaquizgame.TriviaQuizApp.Companion.dataStore
import kotlinx.coroutines.flow.first

object Constants {

    suspend fun setMaxScore(context:Context,value:Int){
        writeToDataStore(context, value = value.toString(),key = BuildConfig.MAX_SCORE_KEY)
    }
    suspend fun getMaxScore(context: Context):Int{
        return readFromDataStore(context, BuildConfig.MAX_SCORE_KEY)?.toInt() ?: 0
    }

    private suspend fun writeToDataStore(context: Context, value:String, key:String) = context.dataStore.edit { settings -> settings[stringPreferencesKey(key)] = value }

    private suspend fun readFromDataStore(context: Context, key: String) = context.dataStore.data.first()[stringPreferencesKey(key)]
}