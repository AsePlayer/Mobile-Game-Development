package com.ryan.datetodatechallenge

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.ComponentActivity
import java.util.*
import android.widget.CalendarView
import android.widget.TextView
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    lateinit var dateTV1: TextView
    lateinit var dateTV2: TextView
    lateinit var calendarView1: CalendarView
    lateinit var calendarView2: CalendarView // new variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the views by their IDs
        calendarView1 = findViewById(R.id.calendarView1)
        calendarView2 = findViewById(R.id.calendarView2)
        dateTV1 = findViewById(R.id.idTVDate1)
        dateTV2 = findViewById(R.id.idTVDate2)


        // initialize new variable

        calendarView1.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dateString = "${month+1}/$dayOfMonth/$year"
            dateTV1.text = dateString
        }

        calendarView2.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val dateString = "${month+1}/$dayOfMonth/$year"
            dateTV2.text = dateString
        }

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        calculateButton.setOnClickListener { calculateTimeDifference(it, dateTV1, dateTV2, resultTextView) }
    }
}

fun calculateTimeDifference(view: View, dateTV1: TextView, dateTV2: TextView, resultTextView: TextView) {
    val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    val date1 = dateFormat.parse(dateTV1.text.toString())
    val date2 = dateFormat.parse(dateTV2.text.toString())

    val diffInMilliSec = date2.time - date1.time
    val diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMilliSec)
    val diffInWeeks = diffInDays / 7
    val diffInMonths = (diffInDays / 30.4368).toInt()
    val diffInYears = (diffInDays / 365.2425).toInt()

    val result = "In Years: $diffInYears\nIn Months: $diffInMonths\nIn Weeks: $diffInWeeks\nIn Days: $diffInDays\n"

    resultTextView.text = result
}
