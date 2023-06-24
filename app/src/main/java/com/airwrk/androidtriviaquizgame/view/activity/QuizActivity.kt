package com.airwrk.androidtriviaquizgame.view.activity

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airwrk.androidtriviaquizgame.R
import com.airwrk.androidtriviaquizgame.databinding.ActivityQuizBinding
import com.airwrk.androidtriviaquizgame.model.Question
import com.airwrk.androidtriviaquizgame.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class QuizActivity : AppCompatActivity() {

    private lateinit var binding:ActivityQuizBinding
    lateinit var viewModel: QuizViewModel
    private lateinit var listQuestions:List<Question>


    private lateinit var colorStateList: ColorStateList
    private var questionCounter = 0
    private var questionCountTotal = 0
    private lateinit var currentQuestion:Question

    private var score = 0
    private var isAnswered by Delegates.notNull<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        viewModel.questionsLiveData.observe(this){
            listQuestions = it
            colorStateList = binding.radioButton1.textColors
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
        isAnswered = true
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
            }else{
                finishQuiz()
            }
        }
    }

    private fun finishQuiz() {
        finish()
    }
}