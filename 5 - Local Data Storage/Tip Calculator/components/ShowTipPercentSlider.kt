package com.ryan.tipcalculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryan.tipcalculator.TipViewModel
import java.text.NumberFormat


@Composable
public fun ShowTipPercentSlider(tipViewModel: TipViewModel) {
    // show the slider, label and calculated tip amount
    var sliderState = remember { mutableStateOf(0f) }

    Card(
        elevation = 5.dp,
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // two types of formatting used: $ and currency
            val str = String.format(
                "Tip Percent: %.1f%%  Tip Value: " + NumberFormat.getCurrencyInstance()
                    .format(tipViewModel.tipValue), tipViewModel.tipPercent
            )
            Text(str)
            Slider(value = sliderState.value,
                valueRange = 0f..100f,
                onValueChange = {
                    // called every time the slider moves
                    sliderState.value = it
                    tipViewModel.tipPercent = sliderState.value
                    tipViewModel.calculateTipValues()
                }
            )

            // setup a row of buttons for user convenience - optional feature
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 10f
                    tipViewModel.tipPercent = sliderState.value })
                {
                    Text("10%")
                }
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 15f
                    tipViewModel.tipPercent = sliderState.value })
                {
                    Text("15%")
                }
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 20f
                    tipViewModel.tipPercent = sliderState.value })
                {
                    Text("20%")
                }
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 25f
                    tipViewModel.tipPercent = sliderState.value
                })
                {
                    Text("25%")
                }
            } // row
        } // column
    } // card
}