package com.ryan.timezonechallenge

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ryan.timezonechallenge.ui.theme.TimezoneChallengeTheme
import java.util.*

class MainActivity : ComponentActivity() {

    // This function is called when the activity is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimezoneChallengeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main("Ryan Scott", this)
                }
            }
        }
    }
}

@Composable
fun Main(name: String, context: Context) {

    // The main UI of the app is defined here using Jetpack Compose UI
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Get the current time for the device's time zone
        val calendar = Calendar.getInstance()
        Text("Local time: ${calendar.time}")
        Text("Time in other time zones:")

        // Define a list of time zone data for different cities
        val timeZones = listOf(
            TimeZoneInfo("Europe/London", 7.0),
            TimeZoneInfo("America/New_York", 5.0),
            TimeZoneInfo("Asia/Tokyo", 7.0),
            TimeZoneInfo("Asia/Delhi", 12.5)
        )

        // Loop through the list of time zones and display the time in each zone
        for (timeZoneData in timeZones) {
            // Get the time zone object for the current city
            val timeZone = TimeZone.getTimeZone(timeZoneData.name)

            // Calculate the offset of the time zone from GMT
            val offset = timeZone.getOffset(Date().time).toFloat() / (60 * 60 * 1000) + timeZoneData.offset

            // Calculate the offset in hours and minutes
            val offsetHours = offset.toInt()
            val offsetMinutes = ((offset - offsetHours) * 60).toInt()

            // Create a new calendar object for the current time zone
            val newTime = Calendar.getInstance(timeZone)

            // Set the time of the new calendar object to the current time
            newTime.timeInMillis = System.currentTimeMillis()

            // Add the offset to the new calendar object
            newTime.add(Calendar.HOUR_OF_DAY, offsetHours)
            newTime.add(Calendar.MINUTE, offsetMinutes)

            // Get the drawable resource for the flag of the current city's country
            val flagDrawableRes = getFlagDrawableRes(timeZoneData.name)

            // Display the flag and the time in the current city
            Row(
                modifier = Modifier.padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = flagDrawableRes),
                    contentDescription = "Flag for ${timeZoneData.name}",
                    Modifier.size(40.dp)
                )

                Text(
                    text = "${timeZoneData.name}: ${newTime.time}       ",
                    Modifier.padding(start = 18.dp)
                )
            }
        }
    }
}

// This function returns the drawable resource for the flag of the specified time zone
fun getFlagDrawableRes(timeZoneName: String): Int {
    return when (timeZoneName) {
        "Europe/London" -> R.drawable.uk_flag
        "America/New_York" -> R.drawable.us_flag
        "Asia/Tokyo" -> R.drawable.japan_flag
        "Asia/Delhi" -> R.drawable.india_flag
        else -> R.drawable.default_flag
    }
}