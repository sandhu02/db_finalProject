package com.example

import com.example.data.Airline
import com.example.data.BookingPaymentRequest
import com.example.data.Flight
import com.example.data.User
import com.exampleu.*
import com.mongodb.client.model.Filters
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.match

fun Routing.requestRoutes() {
    route("/airlines") {
        post {
            //create new airline
            val airline = call.receive<Airline>()
            airlineCollection.insertOne(airline)
            call.respondText ("Airline added successfully")
        }

        get {
            //get all airlines
            val airlines = airlineCollection.find().toList()
            call.respond( HttpStatusCode.OK,
                airlines
            )
        }

//        get ("/{airlinename}"){ //get flights by name
//            val airlinename = call.parameters["airlinename"]?:return@get call.respondText(
//                "Missing or malformed airline name", status = HttpStatusCode.BadRequest
//            )
//            val airline = airlineCollection.findOne(Airline::name eq airlinename)
//            if (airline !== null) {
//                call.respond(airline)
//            } else {
//                call.respondText("Airline not found" ,status = HttpStatusCode.NotFound)
//            }
//        }

        get ("/{airlineId}") {
            val airlineId = call.parameters["airlineId"]?.toIntOrNull()
                ?:return@get call.respondText(
                    "Missing or malformed airline id", status = HttpStatusCode.BadRequest
            )
            println("********8  $airlineId  ***********")
            val airline = airlineCollection.findOne(Airline::airlineId eq airlineId)
            if (airline !== null) {
                call.respond(airline)
            }
            else {
                println("******** User not found  **********")
                call.respondText("Airline not found" ,status = HttpStatusCode.NotFound)
            }
        }
    }

    route("/flights") {
        post{

        }
        get{
            val flights = flightCollection.find().toList()
            call.respond( HttpStatusCode.OK,flights)
        }
        get("/{origin}/{destination}") {
            val originCity = call.parameters["origin"]
            val destinationCity = call.parameters["destination"]

            // Ensure that the parameters are not null
            if (originCity != null && destinationCity != null) {
                // Assuming `flightCollection` is your MongoDB collection
                val flights = flightCollection.aggregate(
                    listOf(
                        match(Filters.eq("origin", originCity)),
                        match(Filters.eq("destination", destinationCity))
                    )
                ).toList()

                // Respond with the flights or an appropriate response
                call.respond(flights)
            } else {
                call.respond("Invalid request parameters")
            }
        }

        get("/{flightid}"){
            val flightId = call.parameters["flightid"]?.toIntOrNull()
                ?:return@get call.respondText(
                "Missing or malformed flightid", status = HttpStatusCode.BadRequest
            )
            val flight = flightCollection.findOne(Flight::flightId eq flightId )
            if (flight !== null) {
                call.respond(flight)
            }
            else {
                call.respondText("Flight not found" ,status = HttpStatusCode.NotFound)
            }
        }
    }

    route ("/users") {
        post ("/{user}") {
            try {
                val user = call.receive<User>()
                userCollection.insertOne(user)
                call.respondText("User added successfully" , status = HttpStatusCode.OK)
            }
            catch (e: Exception) {
                println(e.localizedMessage)
                call.respondText(
                    text = "Failed to add user: ${e.localizedMessage}",
                    status = HttpStatusCode.BadRequest
                )
            }
        }

        get ("/{username}") {
            val username = call.parameters["username"] ?: return@get call.respondText(
                "Missing or malformed username", status = HttpStatusCode.BadRequest
            )
            val user = userCollection.findOne(User::username eq username)
            if (user !== null) {
                call.respond(user)
                println("********* User :  $user **********")
            }
            else {
                call.respondText("User not found" ,status = HttpStatusCode.NotFound)
            }
        }
    }

    route ("/bookings"){
        post ("/{bookingPayment}") {
            try {
                val paymentRequest = call.receive<BookingPaymentRequest>()
                val booking = paymentRequest.booking
                val payment = paymentRequest.payment
                paymentCollection.insertOne(payment)
                val changedbooking = booking.copy(paymentId = payment._id)
                bookingCollection.insertOne(changedbooking)

                call.respondText("Booking added successfully" , status = HttpStatusCode.OK)
            }
            catch (e: Exception) {
                println("*******88  exception occured **********")
                println(e.localizedMessage)
                call.respondText("Failed to add booking: ${e.localizedMessage}", status = HttpStatusCode.BadRequest)
            }
        }

        get () {
            //TODO
        }
    }
}
