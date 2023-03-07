package com.ryan.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ryan.tipcalculator.ui.theme.TipCalculatorTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    // variable for cost of food. This will be moved to a viewModel in future revision
    var mealCost by remember {mutableStateOf(10f)}

    // this will be moved to a viewModel in future revision
    var friendCount by remember { mutableStateOf(2)}

    // 0% to 100% of meal value
    var tipPercent by remember { mutableStateOf(10f)}

    // tipValue = meal * tipPercent
    var tipValue by remember { mutableStateOf(0f)}

    // outline of the four components in this app
    Column(modifier = Modifier.padding(10.dp)) {
        ShowMealCostDataEntry(mealCost, updateMealPrice = {
            // 'it' = parameter. i.e. 'inputValue' in fun.
            mealCost = it
        })
        ShowNumberOfFriendsEntry(friendCount) {
            friendCount = it
        }
        ShowTipPercentSlider(tipPercent, tipValue) {
            tipPercent = it
            tipValue = mealCost * tipPercent / 100
        }
        ShowTotals()
    }
}

@Composable
private fun ShowMealCostDataEntry(mealCost: Float, updateMealPrice: (Float) -> Unit) {
    TextField(
        value = mealCost.toString(),
        label = { Text(text="Cost of the meal")},
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.h5,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = {
            // attempt to convert the input value to a float
            val inputValue = it.toFloatOrNull()
            if(inputValue != null) {
                // run the callback function -- see second item in the parameter list
                updateMealPrice(inputValue)
            }
            else {
                Log.d("ShowMealCost","could not convert to float")
            }
        }
    )
}

@Composable
private fun ShowNumberOfFriendsEntry(friendCount: Int, updateFriendCount: (Int) -> Unit) {
    // card is a container control to put a pretty rectangle around related items
    Card(
        // elevation is the shadow effect
        elevation = 10.dp,
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            var counterState by remember { mutableStateOf(1)}
            Text("Number of people in group:", Modifier.padding(bottom = 10.dp))
            // must use Row to line up the three items horizontally
            Row(modifier = Modifier.padding(bottom = 15.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                // item #1 in row is a round button
                Button(onClick = {
                    // no negative numbers allowed
                    if(counterState > 1)
                        counterState--
                    // send the updated value to the callback function
                    updateFriendCount(counterState)

                },
                modifier = Modifier.size(40.dp),
                    shape = CircleShape
                    ) {
                    Text("-")
                }

                // item #2 in row is an integer
                Text("$counterState",
                    Modifier.padding(10.dp),
                    // larger font than default size
                    style = MaterialTheme.typography.h5)

                // item #3 in row is another round button
                Button(onClick = {
                    // up to 10 friends can play!
                    if (counterState < 10)
                        counterState++
                    updateFriendCount(counterState)
                },
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape
                ) {
                    Text("+")
                } // end button
            } // end row
        } // end column
    } // end card
} // end showfriendscounter function

@Composable
private fun ShowTipPercentSlider(tipPercent: Float, tipValue: Float, updatePercent: (Float) -> Unit) {
    // show the slider, label and calculated tip amount
    var sliderState = remember { mutableStateOf(0f)}

    Card(
        elevation = 5.dp,
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            // two types of formatting used: $ and currency
            val str = String.format(
                "Tip Percent: %.1f%%  Tip Value: " + NumberFormat.getCurrencyInstance()
                    .format(tipValue), tipPercent
            )
            Text(str)
            Slider(value = sliderState.value,
                valueRange = 0f..100f,
                onValueChange = {
                    // called every time the slider moves
                    sliderState.value = it
                    updatePercent(sliderState.value)
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
                    updatePercent(sliderState.value)
                })
                {
                    Text("10%")
                }
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 15f
                    updatePercent(sliderState.value)
                })
                {
                    Text("15%")
                }
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 20f
                    updatePercent(sliderState.value)
                })
                {
                    Text("20%")
                }
                Button(onClick = {
                    // set the slide value and update the tip amount
                    sliderState.value = 25f
                    updatePercent(sliderState.value)
                })
                {
                    Text("25%")
                }
            } // row
        } // column
    } // card
}

@Composable
private fun ShowTotals() {
    Text("Total cost: $11.50")
    Text("Cost for each friend: $6.25")
}
