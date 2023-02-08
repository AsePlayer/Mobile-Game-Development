package com.ryan.temperatureconversion

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.math.roundToInt

class ViewModelForTemperatures : ViewModel() {
    // state of current temp. This can be toggled with the switch control.
    var isFahrenheit by mutableStateOf(true)
    // calculated result. This will be updated when the calculateConversion function is called.
    var convertedTemperature by mutableStateOf("")

    fun doSwitchToggle() {
        isFahrenheit = !isFahrenheit
    }

    fun calculateConversion(inputValue: String) {
        convertedTemperature = try {
            val temperature = inputValue.toInt()

            if(isFahrenheit) {
                // convert to F /U2103 = C
                ((temperature - 32) * 5/9.0).roundToInt().toString() + "\u2103"
            } else {
                // convert to F /U2189 = F
                ((temperature * 9/5f) + 32).roundToInt().toString() + "\u2109"
            }
        } catch(e: Exception) {
            // something went wrong with .toInt() conversion. User probably input invalid data.
            "Error"
        }
    }
}