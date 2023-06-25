package com.airwrk.androidtriviaquizgame.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.airwrk.androidtriviaquizgame.databinding.ActivityMainBinding
import com.airwrk.androidtriviaquizgame.model.Question
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

        viewModel.questionsLiveData.observe(this){
            goToQuizPage()
        }


        viewModel.questionsLiveDataWithID.observe(this){
            val visitedQuestions = ArrayList<Question>()
            var notVisitedQuestions = 0
            it.forEach {
                if(it.isDisplayed == "yes"){
                    visitedQuestions.add(it)
                }
            }
            if(it.size - visitedQuestions.size < 10){
                viewModel.downLoadQuestions()
            } else{
                goToQuizPage()
            }
        }


        binding.apply {
            buttonQuizHistory.setOnClickListener {
                Intent(this@MainActivity,HistoryActivity::class.java).apply {
                    startActivity(this)
                }
            }
            buttonStartQuiz.setOnClickListener {
                viewModel.getAllQuestionsFromDB()
            }

        }




        setContentView(binding.root)

    }

    private fun goToQuizPage() {
        Intent(this@MainActivity,QuizActivity::class.java).apply {
            startActivity(this)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMaxScore(this)
    }
}