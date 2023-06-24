package com.airwrk.androidtriviaquizgame.view.activity

import android.os.Bundle
import com.airwrk.androidtriviaquizgame.databinding.ActivityMainBinding
import com.airwrk.androidtriviaquizgame.view.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)




        setContentView(binding.root)

    }
}