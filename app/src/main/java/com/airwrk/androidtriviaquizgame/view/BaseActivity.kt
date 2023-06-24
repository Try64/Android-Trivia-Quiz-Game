package com.airwrk.androidtriviaquizgame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {


    /**
     * Base class which should be applied
     * where common activity functionalities
     * can be found
     *
     * @param savedInstanceState this will not be used as we will persist data using DataStore
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}