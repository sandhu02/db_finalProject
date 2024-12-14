package org.example.project.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun homeScreen(currentScreen: MutableState<String>) {
    val textfieldColor : TextFieldColors = TextFieldDefaults.textFieldColors(
        backgroundColor = Color.Transparent, // No background color
        focusedIndicatorColor = Color.Blue, // Color of the underline when focused
        unfocusedIndicatorColor = Color.Gray, // Color of the underline when not focused
        textColor = Color.White
    )
    val buttonColor = ButtonDefaults.buttonColors(
        backgroundColor = Color(0xFF00684a), // Custom background color
        contentColor = Color.White // Custom text color
    )

    Surface (modifier = Modifier.fillMaxSize() , color = bgColor) {
        Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = "Search Flights",
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(16.dp),
                color = headerColor
            )

            Row {
                var departureCity by remember { mutableStateOf(TextFieldValue("")) }
                var arrivalCity by remember { mutableStateOf(TextFieldValue("")) }

                OutlinedTextField(
                    value = departureCity,
                    onValueChange = {departureCity = it},
                    label = { Text("Departure City" , color = textColor) },
                    modifier = Modifier.padding(16.dp),
                    colors = textfieldColor
                )
                OutlinedTextField(
                    value = arrivalCity,
                    onValueChange = {arrivalCity = it},
                    label = { Text("Destination City" , color = textColor) },
                    modifier = Modifier.padding(16.dp),
                    colors = textfieldColor
                )
            }

            Button(
                onClick = {
                    currentScreen .value = "flightScreen"
                },
                colors = buttonColor ,
                modifier = Modifier.padding(12.dp)
            ) {
                Text(" Search " , style = MaterialTheme.typography.button)
            }
            Text(
                text = "Logout" ,
                modifier = Modifier.padding(16.dp).clickable {
                    currentScreen.value = "loginScreen"
                } ,
                style = TextStyle(textDecoration = TextDecoration.Underline , color = textColor),
                color = textColor
            )
        }
    }
}