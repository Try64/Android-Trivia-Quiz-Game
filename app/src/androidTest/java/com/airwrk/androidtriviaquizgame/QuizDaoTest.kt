package com.airwrk.androidtriviaquizgame

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.airwrk.androidtriviaquizgame.db.QuestionsDAO
import com.airwrk.androidtriviaquizgame.db.QuizDB
import com.airwrk.androidtriviaquizgame.model.Question
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
@HiltAndroidTest
class QuizDaoTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var quizDatabase: QuizDB
    private lateinit var questionsDAO: QuestionsDAO

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        questionsDAO = quizDatabase.getQuestionsDAO()
    }

    @Test
    fun insertProduct_returnsSingleProduct() = runTest {
        val question = Question(id = null, question = "What is Airwrk?", option1 = "A Transport Service", option2 = "A Charity Fund", option3 = "A Broking Agency", option4 = "A Privet Company", ans = "A Privet Company", isDisplayed = "no")
        questionsDAO.addQuestion(listOf(question))
        val result = questionsDAO.getQuestions()
        Assert.assertEquals(1, result.size)
    }


    @After
    fun closeDatabase() {
        quizDatabase.close()
    }
}