package com.ryan.tipcalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

// viewModel will allow all properties and calculations to be done in this file.
class TipViewModel {
    // variable for cost of food. This will be moved to a viewModel in future revision
    var mealCost by mutableStateOf(10f)

    // this will be moved to a viewModel in future revision
    var friendCount by mutableStateOf(2)

    // 0% to 100% of meal value
    var tipPercent by mutableStateOf(10f)

    // tipValue = meal * tipPercent
    var tipValue by mutableStateOf(0f)

    // meal + tip value
    var totalBill by mutableStateOf(0f)

    // totalBill / friendCount
    var totalPerPerson by mutableStateOf(0f)

    fun calculateTipValues() {
        tipValue = tipPercent * mealCost / 100
        totalBill = mealCost + tipValue
        totalPerPerson = totalBill / friendCount
    }

}