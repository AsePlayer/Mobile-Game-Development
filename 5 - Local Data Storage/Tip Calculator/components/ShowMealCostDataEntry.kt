package com.ryan.tipcalculator.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.ryan.tipcalculator.TipViewModel

@Composable
public fun ShowMealCostDataEntry(tipViewModel: TipViewModel) {
    TextField(
        value = tipViewModel.mealCost.toString(),
        label = { Text(text="Cost of the meal") },
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.h5,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            // attempt to convert the input value to a float
            val inputValue = it.toFloatOrNull()
            if(inputValue != null) {
                // run the callback function -- see second item in the parameter list
                tipViewModel.calculateTipValues()
            }
            else {
                Log.d("ShowMealCost","could not convert to float")
            }
        }
    )
}
