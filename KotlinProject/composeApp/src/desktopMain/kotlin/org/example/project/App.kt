package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.example.project.Screens.*

lateinit var originCitytemp : String
lateinit var destinationCitytemp : String

@Composable
fun App() {
    var currentScreen = remember { mutableStateOf("loginScreen") }

    when (currentScreen.value) {
        "loginScreen" -> loginScreen(currentScreen )
        "registerScreen" -> registerScreen(currentScreen)
        "homeScreen" -> homeScreen(currentScreen)
        "searchedFlightsScreen" -> searchedFlightsScreen(currentScreen ,origin =  originCitytemp , destination = destinationCitytemp)
        "flightScreen" -> flightScreen(currentScreen)
        "bookTicketScreen" -> bookTicket(currentScreen,flightId = tempflightId , airlineId = tempairlineId)
    }
}
