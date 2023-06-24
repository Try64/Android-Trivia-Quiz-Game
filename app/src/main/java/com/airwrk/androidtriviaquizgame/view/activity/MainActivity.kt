package com.airwrk.androidtriviaquizgame.view.activity

import android.content.Intent
import android.os.Bundle
import com.airwrk.androidtriviaquizgame.databinding.ActivityMainBinding
import com.airwrk.androidtriviaquizgame.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)


        binding.apply {
            buttonQuizHistory.setOnClickListener {

            }
            buttonStartQuiz.setOnClickListener {
                Intent(this@MainActivity,QuizActivity::class.java).apply {
                    startActivity(this)
                }
            }

        }




        setContentView(binding.root)

    }
}