package org.example.project.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.data.Flight
import org.example.project.retrofit.FetchFlight

var tempflightId = 0

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun flightCard(currentScreen: MutableState<String>, flight: Flight) {
    Card(
        backgroundColor = cardColor ,
        contentColor = Color.White,
        elevation = 4.dp ,
        modifier = Modifier.padding(12.dp).width(500.dp),
        onClick = {
            tempflightId = flight.flightId
            currentScreen.value = "bookTicketScreen"
        }
    ) {
        Column (verticalArrangement = Arrangement.SpaceEvenly , modifier = Modifier.padding(16.dp)) {
            Text(text = "${flight.origin} - ${flight.destination}" , style = MaterialTheme.typography.h4 )
            Text(text = "${flight.departureTime} - ${flight.arrivalTime}" , style = MaterialTheme.typography.body1 )
        }
    }
}

@Composable
fun flightScreen(currentScreen: MutableState<String> ) {
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = Color(0xFF00684a), // Custom background color
        contentColor = Color.White // Custom text color
    )

    val flightsCall = remember { FetchFlight() }
    val flightsList: List<Flight>? = flightsCall.flightslist
    LaunchedEffect(Unit) {
        flightsCall.fetchFlightsList()
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
                    Text("Go back")
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