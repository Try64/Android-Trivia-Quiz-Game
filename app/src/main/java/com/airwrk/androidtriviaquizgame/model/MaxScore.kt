package com.airwrk.androidtriviaquizgame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MaxScore(@PrimaryKey(autoGenerate = true) var id:Int,var score:Int = 0)
