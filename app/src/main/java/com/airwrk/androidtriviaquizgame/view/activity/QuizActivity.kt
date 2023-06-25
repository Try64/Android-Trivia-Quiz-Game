package com.airwrk.androidtriviaquizgame.view.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airwrk.androidtriviaquizgame.R
import com.airwrk.androidtriviaquizgame.databinding.ActivityQuizBinding
import com.airwrk.androidtriviaquizgame.model.History
import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import kotlin.properties.Delegates

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    private lateinit var binding:ActivityQuizBinding
    lateinit var viewModel: QuizViewModel
    private lateinit var listQuestions:List<Question>


    private lateinit var colorStateList: ColorStateList
    private lateinit var colorStateTimerList: ColorStateList
    private var questionCounter = 0
    private var questionCountTotal = 0
    private lateinit var currentQuestion:Question

    private var score = 0
    private var isAnswered by Delegates.notNull<Boolean>()

    private val timeOut = 30_000L
    private lateinit var timer:CountDownTimer
    private var timeLeftInMillis by Delegates.notNull<Long>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]
        viewModel.getAllQuestionsFromDB()

        viewModel.questionsLiveDataWithID.observe(this){
            val unvisited = ArrayList<Question>()
            var index = 0
            while(unvisited.size < 5){
                if(it[index].isDisplayed == "no"){
                    unvisited.add(it[index])
                }
                index++
            }
            listQuestions = unvisited.toList()
            colorStateList = binding.radioButton1.textColors
            colorStateTimerList = binding.textViewCountdown.textColors
            questionCountTotal = listQuestions.size

            showNextQuestion()
        }

        binding.apply {
            buttonConfirmNext.setOnClickListener{
                if(!isAnswered){
                    if(radioButton1.isChecked||
                            radioButton2.isChecked||
                            radioButton3.isChecked||
                            radioButton4.isChecked){
                        checkAns()
                    }else{
                        Toast.makeText(this@QuizActivity,"Please select an answer",Toast.LENGTH_LONG).show()
                    }
                }else{
                    showNextQuestion()
                }
            }
        }


    }

    private fun checkAns() {
        if(timer != null){
            timer.cancel()
        }
        isAnswered = true
        currentQuestion.isDisplayed = "yes"
        viewModel.updateQuestion(currentQuestion)
        when(binding.radioGroup.checkedRadioButtonId){
            binding.radioButton1.id ->{
                if(binding.radioButton1.text == currentQuestion.ans){
                    score++
                }else{
                    if(score>0){
                        score--
                    }
                }
                binding.textViewScore.text = "Score: ${score.toString()}"
            }
            binding.radioButton2.id ->{
                if(binding.radioButton2.text == currentQuestion.ans){
                    score++
                }else{
                    if(score>0){
                        score--
                    }
                }
                binding.textViewScore.text = "Score: ${score.toString()}"
            }
            binding.radioButton3.id ->{
                if(binding.radioButton3.text == currentQuestion.ans){
                    score++
                }else{
                    if(score>0){
                        score--
                    }
                }
                binding.textViewScore.text = "Score: ${score.toString()}"
            }
            binding.radioButton4.id ->{
                if(binding.radioButton4.text == currentQuestion.ans){
                    score++
                }else{
                    if(score>0){
                        score--
                    }
                }
                binding.textViewScore.text = "Score: ${score.toString()}"
            }
        }
        showSolution()
    }

    private fun showSolution() {
        binding.apply {
            radioButton1.setTextColor(Color.RED)
            radioButton2.setTextColor(Color.RED)
            radioButton3.setTextColor(Color.RED)
            radioButton4.setTextColor(Color.RED)

            if(radioButton1.text == currentQuestion.ans){
                radioButton1.setTextColor(Color.GREEN)
                textViewQuestion.text = "${currentQuestion.ans} is correct."
            }
            if(radioButton2.text == currentQuestion.ans){
                radioButton2.setTextColor(Color.GREEN)
                textViewQuestion.text = "${currentQuestion.ans} is correct."
            }
            if(radioButton3.text == currentQuestion.ans){
                radioButton3.setTextColor(Color.GREEN)
                textViewQuestion.text = "${currentQuestion.ans} is correct."
            }
            if(radioButton4.text == currentQuestion.ans){
                radioButton4.setTextColor(Color.GREEN)
                textViewQuestion.text = "${currentQuestion.ans} is correct."
            }
            if(questionCounter<questionCountTotal){
                buttonConfirmNext.text = "Next"
            }else{
                buttonConfirmNext.text = "Finish"
                viewModel.updateMaxScore(this@QuizActivity,score)
                viewModel.recordHistory(History(date = System.currentTimeMillis().toString(), score = score,id = null))
            }

        }
    }


    private fun showNextQuestion(){
        binding.apply {
            radioButton1.setTextColor(colorStateList)
            radioButton2.setTextColor(colorStateList)
            radioButton3.setTextColor(colorStateList)
            radioButton4.setTextColor(colorStateList)
            radioGroup.clearCheck()

            if(questionCounter<questionCountTotal){
                currentQuestion = listQuestions[questionCounter]

                textViewQuestion.text = currentQuestion.question
                radioButton1.text = currentQuestion.option1
                radioButton2.text = currentQuestion.option2
                radioButton3.text = currentQuestion.option3
                radioButton4.text = currentQuestion.option4

                questionCounter++
                textViewQuestionCount.text = "Question: ${questionCounter}/${questionCountTotal}"
                isAnswered = false
                buttonConfirmNext.text = "Confirm"

                timeLeftInMillis = timeOut
                startCountDown()

            }else{
                finishQuiz()
            }
        }
    }

    private fun startCountDown() {
        timer = object : CountDownTimer(timeLeftInMillis,1000L){
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                timeLeftInMillis = 0
                updateCountDownText()
                handleTimeOutEvent()
            }

        }.start()
    }

    private fun handleTimeOutEvent() {
        if(score > 0){
            score--
        }
        isAnswered = true
        currentQuestion.isDisplayed = "yes"
        viewModel.updateQuestion(currentQuestion)
    }

    private fun updateCountDownText() {
        val minutes =  ((timeLeftInMillis/1000)/60).toInt()
        val seconds =  ((timeLeftInMillis/1000)%60).toInt()
        val formattedString = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds)
        binding.textViewCountdown.text = formattedString
        if(timeLeftInMillis < 10_000){
            binding.textViewCountdown.setTextColor(Color.RED)
        }else{
            binding.textViewCountdown.setTextColor(colorStateTimerList)
        }
    }

    private fun finishQuiz() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(timer != null){
            timer.cancel()
        }
    }
}