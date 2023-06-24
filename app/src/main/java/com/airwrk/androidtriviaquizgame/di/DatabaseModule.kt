package com.airwrk.androidtriviaquizgame.di

import android.content.Context
import androidx.room.Room
import com.airwrk.androidtriviaquizgame.db.QuizDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFakerDB(@ApplicationContext context : Context) : QuizDB{
        return Room.databaseBuilder(context, QuizDB::class.java, "QuizDB").build()
    }
}