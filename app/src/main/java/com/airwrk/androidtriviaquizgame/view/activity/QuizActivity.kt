package com.airwrk.androidtriviaquizgame.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airwrk.androidtriviaquizgame.R
import com.airwrk.androidtriviaquizgame.databinding.ActivityQuizBinding
import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    private lateinit var binding:ActivityQuizBinding
    lateinit var viewModel: QuizViewModel
    private lateinit var listQuestions:List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        viewModel.questionsLiveData.observe(this){
            listQuestions = it
        }

    }
}