package com.airwrk.androidtriviaquizgame.extentions

import java.text.SimpleDateFormat
import java.util.Calendar

object ExtWithEachClass {
    //ie. "dd/MM/yyyy hh:mm:ss.SSS"
    fun String.getDate(epoch:Long):String{
        // Create a DateFormatter object for displaying date in specified format.
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(this)

        // Create a calendar object that will convert the date and time value in milliseconds to date.

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = epoch
        return formatter.format(calendar.time)
    }
}