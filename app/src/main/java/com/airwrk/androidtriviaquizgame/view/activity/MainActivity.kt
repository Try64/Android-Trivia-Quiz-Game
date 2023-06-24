package com.airwrk.androidtriviaquizgame.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airwrk.androidtriviaquizgame.databinding.ActivityMainBinding
import com.airwrk.androidtriviaquizgame.view.BaseActivity
import com.airwrk.androidtriviaquizgame.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding:ActivityMainBinding
    lateinit var viewModel: QuizViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        viewModel.maxScoreLD.observe(this){
            binding.textViewHighscore.text = "Highscore: $it"
        }


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

    override fun onResume() {
        super.onResume()
        viewModel.getMaxScore(this)
    }
}