package com.ryan.tipcalculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ryan.tipcalculator.TipViewModel
import java.text.NumberFormat

@Composable
public fun ShowTotals(tipViewModel: TipViewModel) {
    // first card shows cost for each person
    Card (
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp, top = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(10.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = "Each person pays:",
            )
            // format for $
            val formattedPerPerson = NumberFormat.getCurrencyInstance().format(tipViewModel.totalPerPerson)
            Text(
                style = MaterialTheme.typography.h4,
                text = "$formattedPerPerson"
            )
        }
    }
    // second card shows cost for each person
    Card (
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color.LightGray)
                .padding(10.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h6,
                text = "Total price:",
            )
            // format for $
            val formattedTotal = NumberFormat.getCurrencyInstance().format(tipViewModel.totalBill)
            Text(
                style = MaterialTheme.typography.h4,
                text = "${formattedTotal}"
            )
        } // end column

    } // end function
} // end MyApp