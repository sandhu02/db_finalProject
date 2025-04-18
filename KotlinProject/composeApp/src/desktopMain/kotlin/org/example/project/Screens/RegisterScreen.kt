package org.example.project.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.data.User
import org.example.project.retrofit.FetchUser

@Composable
fun registerScreen(currentScreen: MutableState<String>) {
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

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var actualname by remember { mutableStateOf("") }

    var registeredPopup = remember { mutableStateOf(false) }

    var callFunction = remember { mutableStateOf(false) }

    val userCall = remember { FetchUser() }
    if (callFunction.value) {
        val userObject = User(username = username, password = password , name = actualname)
        LaunchedEffect(Unit) {
            userCall.insertOneUser(userObject)
            registeredPopup.value = true
            callFunction.value = false
        }
    }

    Surface (modifier = Modifier.fillMaxSize() , color = bgColor) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Register" ,
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(16.dp),
                color = headerColor
            )

            Box(){
                Column {
                    TextField(
                        value = actualname,
                        onValueChange = { actualname = it },
                        label = { Text("Enter Your Name" , color = textColor) },
                        modifier = Modifier.padding(16.dp),
                        colors = textfieldColor,
                    )
                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Create a Username" , color = textColor) },
                        modifier = Modifier.padding(16.dp),
                        colors = textfieldColor
                    )
                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Create a Password" , color = textColor) },
                        modifier = Modifier.padding(16.dp),
                        colors = textfieldColor
                    )
                }
            }

            Button(
                onClick = {
                    callFunction.value = true
                } ,
                colors = buttonColor , modifier = Modifier.padding(12.dp)) {
                Text("Register")
            }
            Button(
                onClick = {
                    currentScreen.value = "loginScreen"
                } ,
                colors = buttonColor , modifier = Modifier.padding(16.dp)) {
                Text(" Cancel ")
            }

        }
    }

    if (registeredPopup.value) {
        popupBox(displayStatus = registeredPopup , "User has been registered")
    }
}

