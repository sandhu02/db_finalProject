package org.example.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.data.Airline
import org.example.project.Screens.*
import org.example.project.data.Flight


@Composable
fun App() {
    var currentScreen = remember { mutableStateOf("loginScreen") }

    when (currentScreen.value) {
        "loginScreen" -> loginScreen(currentScreen)
        "registerScreen" -> registerScreen(currentScreen)
        "homeScreen" -> homeScreen(currentScreen)
        "flightScreen" -> flightScreen(currentScreen)
        "bookTicketScreen" -> bookTicket(currentScreen,flightId = tempflightId)
    }
//    bookTicket(
//        flight = Flight(
//            flightId = 1, origin = "origin", destination = "destination", departureTime = "departureTime",
//            arrivalTime = "arrival",
//            passengers = listOf(1,2)
//        )
//    )
}
