package com.airwrk.androidtriviaquizgame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey(autoGenerate = false)
    var id:Int?,
    var question: String? = null,
    var option1: String? = null,
    var option2: String? = null,
    var option3: String? = null,
    var option4: String? = null,
    var ans:String? = null,
    var isDisplayed:String
)
