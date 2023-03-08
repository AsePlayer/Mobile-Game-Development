package com.ryan.tipcalculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryan.tipcalculator.TipViewModel

@Composable
public fun ShowFriendCountEntry(tipViewModel: TipViewModel) {
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
            var counterState by remember { mutableStateOf(1) }
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
                    // update the viewModel
                    tipViewModel.friendCount = counterState
                    tipViewModel.calculateTipValues()

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
                    // update the viewModel
                    tipViewModel.friendCount = counterState
                    tipViewModel.calculateTipValues()
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
