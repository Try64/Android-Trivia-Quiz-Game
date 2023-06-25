package com.airwrk.androidtriviaquizgame.extentions

import java.text.SimpleDateFormat
import java.util.Calendar

object ExtWithEachClass {
    //ie. "dd/MM/yyyy hh:mm:ss.SSS"
    fun String.getDate(epoch:Long):String{
        val formatter = SimpleDateFormat(this)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = epoch
        return formatter.format(calendar.time)
    }
}