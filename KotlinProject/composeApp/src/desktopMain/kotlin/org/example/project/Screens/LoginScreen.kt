package org.example.project.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun loginScreen(currentScreen: MutableState<String>) {
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
                text = "Login to Book Ticket" ,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.padding(16.dp),
                color = headerColor,
            )

            Box {
                Column {
                    var username by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username" , color = textColor) },
                        modifier = Modifier.padding(16.dp),
                        colors = textfieldColor
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password" , color = textColor) },
                        modifier = Modifier.padding(16.dp),
                        colors = textfieldColor
                    )
                }
            }
            Row  {
                Button(
                    onClick = {
                        currentScreen.value = "registerScreen"
                    },
                    colors = buttonColor , modifier = Modifier.padding(12.dp)) {
                    Text("Register" , style = MaterialTheme.typography.button)
                }
                Button(
                    onClick = {
                        currentScreen.value = "homeScreen"
                    },
                    colors = buttonColor , modifier = Modifier.padding(12.dp)) {
                    Text(" Login " , style = MaterialTheme.typography.button)
                }
            }
        }
    }
}