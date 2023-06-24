package com.airwrk.androidtriviaquizgame.di

import com.airwrk.androidtriviaquizgame.retrofit.QuizAPI
import com.airwrk.androidtriviaquizgame.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesFakerAPI(retrofit: Retrofit) : QuizAPI {
        return retrofit.create(QuizAPI::class.java)
    }
}