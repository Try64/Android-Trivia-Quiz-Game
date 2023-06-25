package com.airwrk.androidtriviaquizgame.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class History(@PrimaryKey(autoGenerate = false) var id: Int?, var date: String, var score: Int)
