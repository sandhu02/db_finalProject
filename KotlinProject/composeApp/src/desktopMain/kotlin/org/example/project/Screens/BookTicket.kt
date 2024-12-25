package org.example.project.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import org.example.project.data.*
import org.example.project.retrofit.BookingCall
import org.example.project.retrofit.FetchAirline
import org.example.project.retrofit.FetchFlight
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

@Composable
fun popupBox(displayStatus : MutableState<Boolean> , text :String) {
    Popup (alignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(70.dp)
                .background(color = Color(0xFF023430) , shape = RoundedCornerShape(30.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = text , style = MaterialTheme.typography.h5 , modifier = Modifier.padding(2.dp) , color = textColor)
                Text(
                    "Ok",
                    style = MaterialTheme.typography.body1 ,
                    color = headerColor,
                    modifier = Modifier
                        .padding(bottom = 2.dp)
                        .clickable { displayStatus.value = false }
                )
            }
        }
    }
}


@Composable
fun bookTicket(currentScreen:MutableState<String> ,flightId: Int , airlineId : Int ) {
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = Color(0xFF00684a), // Custom background color
        contentColor = Color.White // Custom text color
    )

    val flightCall = remember { FetchFlight() }
    val airlineCall = remember { FetchAirline() }
    val flight:Flight? = flightCall.oneflight
    val airline:Airline? = airlineCall.fetchedAirline
    LaunchedEffect(airlineId) {
        airlineCall.getAirlinebyId(airlineId)
        println("******  $airline ********")
    }
    LaunchedEffect(flightId) {
        flightCall.fetchOneFlight(flightId)
    }

    var bookticket = remember { mutableStateOf(false) }
    if (flight != null && bookticket.value) {
        val bookingCall = remember { BookingCall() }

        val bookingObject = Booking(
            flightId = flightId,
            seatNumber = Random.nextInt(1,200),
            bookingDate = flight.flightDate,
            username = currentUser.username,
        )
        val paymentObject = Payment(
            amount = flight.ticketPrice,
            date = LocalDate.now().toString(),
            time = LocalTime.now().toString()
        )
        val bookingPayment = BookingPaymentRequest(bookingObject, paymentObject)

        LaunchedEffect(Unit) {
            bookingCall.addbooking(bookingPayment)
        }
    }

    var showbookedpopup = remember { mutableStateOf(false) }

    Surface (modifier = Modifier.fillMaxSize() , color = bgColor){
        Column (horizontalAlignment = Alignment.CenterHorizontally , verticalArrangement = Arrangement.SpaceEvenly) {
            Text("Checkout" , style = MaterialTheme.typography.h1 , color = headerColor , modifier = Modifier.padding(12.dp))

            if (flight != null && airline != null) {
                    Card (modifier = Modifier.padding(4.dp) , backgroundColor = cardColor , contentColor = textColor) {
                        Column (horizontalAlignment = Alignment.Start , verticalArrangement = Arrangement.SpaceEvenly , modifier = Modifier.padding(start = 24.dp , end = 24.dp , top = 24.dp , bottom = 42.dp)) {
                            Text("${flight.origin} - ${flight.destination}" , style = MaterialTheme.typography.h2 )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row (verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Airline : ", style = MaterialTheme.typography.body1 )
                                Spacer(modifier = Modifier.width(36.dp))
                                Text(airline.name , style = MaterialTheme.typography.h3 , color = headerColor )
                            }
                            Text("Departure Time : ${flight.departureTime} ", style = MaterialTheme.typography.body1 )
                            Text("Arrival Time : ${flight.arrivalTime} ", style = MaterialTheme.typography.body1 )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Seats Occupied : ${flight.passengers.size} ", style = MaterialTheme.typography.body1 )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Departs On : ${flight.flightDate}/-", style = MaterialTheme.typography.h6 )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Ticket Price : ${flight.ticketPrice}/-", style = MaterialTheme.typography.h6 )
                        }

                    }
            }
            else {
                Text("No Such Flight Found" , style = MaterialTheme.typography.body1 , color = cardColor)
            }

            Row  {
                Button(
                    onClick = {
                        currentScreen.value = "flightScreen"
                    },
                    modifier = Modifier.padding(12.dp), colors = buttonColor) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        bookticket.value = true
                        showbookedpopup.value = true
                    },
                    modifier = Modifier.padding(12.dp), colors = buttonColor) {
                    Text("Book Ticket")
                }
            }
        }
        if (showbookedpopup.value) {
            popupBox(showbookedpopup , "Ticket Booked")
        }
    }
}