package org.example.project.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.project.data.User
import org.example.project.retrofit.FetchUser

var currentUser : User = User("awais" , "aw12" , "awais")

fun authenticateUser(password : String, user: User?) : Boolean {
    if (user!=null && password == user.password ) {
        return true
    }
    return false
}

@Composable
fun loginScreen(currentScreen: MutableState<String> ) {
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
    var errorText by remember { mutableStateOf("") }

    var callfunction = remember { mutableStateOf(false) }
    var nextScreen = false

    var userCall = remember { FetchUser() }
    val fetchedUser = userCall.oneUser

    if (callfunction.value) {
        print("******** function called *******")
        LaunchedEffect(Unit) {
            userCall.fetchOneUser(username)
            println("********* ${fetchedUser} , $password ********")

            if (authenticateUser(password = password, user = fetchedUser)) {
                println("***** authenticated **********")
                nextScreen = true
            }
            else {
                errorText = "Invalid Username or Password"
            }
            callfunction.value = false
        }
    }

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
                    Text(text = errorText, modifier = Modifier.padding(8.dp) , color = Color.Red , style = MaterialTheme.typography.body2)
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

//                        callfunction.value = true
//                        if (nextScreen) {
//                            currentScreen.value = "homeScreen"
//                        }
//                        else {
//                            println("*** user details don't match ***")
//                        }
                    },
                    colors = buttonColor , modifier = Modifier.padding(12.dp)) {
                    Text(" Login " , style = MaterialTheme.typography.button)
                }

            }
        }
    }
}