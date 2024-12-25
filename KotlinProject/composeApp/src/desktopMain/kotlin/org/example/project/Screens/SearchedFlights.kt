package org.example.project.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.data.Flight
import org.example.project.retrofit.FetchFlight

@Composable
fun searchedFlightsScreen(currentScreen: MutableState<String> , origin:String , destination:String ) {
    val flightsCall = remember { FetchFlight() }
    val flightsList: List<Flight>? = flightsCall.flightslist
    LaunchedEffect(Unit) {
        flightsCall.fetchflightsfilterbyCity(origin, destination)
    }

    Surface(modifier = Modifier.fillMaxSize(), color = bgColor) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(
                    onClick = {
                        currentScreen.value = "homeScreen"
                    },
                    modifier = Modifier.padding(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = bgColor, contentColor = headerColor)
                ) {
                    Text(" <- ")
                }
                Text(
                    text = "Select a Flight",
                    style = MaterialTheme.typography.h2,
                    color = headerColor,
                    modifier = Modifier.padding(12.dp)
                )
            }
            if (flightsList != null) {
                LazyColumn {
                    items(flightsList) { item ->
                        val index = flightsList.indexOf(item)
                        flightCard(currentScreen, item)
                    }
                }
            }
            else {
                Text(text = "No Flights Available", style = MaterialTheme.typography.body1, color = cardColor)
            }
        }
    }
}