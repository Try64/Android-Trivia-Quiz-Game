package com.airwrk.androidtriviaquizgame.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airwrk.androidtriviaquizgame.R
import com.airwrk.androidtriviaquizgame.databinding.ActivityQuizEndBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizEndActivity : AppCompatActivity() {

    private lateinit var binding:ActivityQuizEndBinding
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizEndBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score = intent?.getIntExtra("score",0) ?: 0

        binding.apply {
            textScore.text = "Your Score is $score"
            buttonStartQuiz.setOnClickListener {
                Intent(this@QuizEndActivity,QuizActivity::class.java).apply {
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(this)
                }
                finish()
            }
            buttonQuizHistory.setOnClickListener {
                Intent(this@QuizEndActivity,HistoryActivity::class.java).apply {
                    this.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(this)
                }
                finish()
            }
        }

    }
}