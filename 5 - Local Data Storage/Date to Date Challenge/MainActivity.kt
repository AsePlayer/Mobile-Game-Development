package com.ryan.datetodatechallenge

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryan.datetodatechallenge.components.ShowFriendCountEntry
import com.ryan.datetodatechallenge.components.ShowTipPercentSlider
import com.ryan.datetodatechallenge.components.ShowTotals
import com.ryan.datetodatechallenge.ui.theme.DateToDateChallengeTheme
import com.ryan.tipcalculator.components.*
import java.util.*

import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.TextView

class MainActivity : ComponentActivity() {

    lateinit var dateTV1: TextView
    lateinit var dateTV2: TextView
    lateinit var calendarView1: CalendarView
    lateinit var calendarView2: CalendarView // new variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
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
    }
}