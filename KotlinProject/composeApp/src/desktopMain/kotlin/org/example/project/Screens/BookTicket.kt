package org.example.project.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.data.Flight
import org.example.project.retrofit.FetchFlight

@Composable
fun bookTicket(currentScreen:MutableState<String> ,flightId: Int) {
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = Color(0xFF00684a), // Custom background color
        contentColor = Color.White // Custom text color
    )

    val flightCall = remember { FetchFlight() }
    val flight:Flight? = flightCall.oneflight
    LaunchedEffect(flightId) {
        flightCall.fetchOneFlight(flightId)
    }

    Surface (modifier = Modifier.fillMaxSize()){
        Column (horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {
            if (flight != null) {
                    Text("Checkout" , style = MaterialTheme.typography.h1 , color = headerColor , modifier = Modifier.padding(12.dp))
                    Text("${flight.origin} - ${flight.destination}" , style = MaterialTheme.typography.h4 )
                    Text("Departure Time : ${flight.departureTime} ", style = MaterialTheme.typography.h6 )

                    Text("Arrival Time : ${flight.arrivalTime} ", style = MaterialTheme.typography.h6 )
                    Text("Seats Occupied : ${flight.passengers.size} ", style = MaterialTheme.typography.h6 )

                    Row  {
                        Button(
                            onClick = {
                                currentScreen.value = "flightScreen"
                            }, modifier = Modifier.padding(12.dp), colors = buttonColor) {
                            Text("Cancel")
                        }
                        Button(
                            onClick = {

                            }, modifier = Modifier.padding(12.dp), colors = buttonColor) {
                            Text("Book Ticket")
                        }
                    }
                }
            else {
                Text("No Such Flight Found" , style = MaterialTheme.typography.body1 , color = cardColor)
            }
        }
    }
}