package com.example

import com.example.data.Flight
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.eq
import org.litote.kmongo.find
import org.litote.kmongo.findOne

fun Routing.requestRoutes() {
    route("/airlines") {
        post {
            //create new airline
            val airline = call.receive<Airline>()
            userCollection.insertOne(airline)
            call.respondText ("User added successfully")
        }

        get {
            //get all airlines
            val airlines = userCollection.find().toList()
            call.respond( HttpStatusCode.OK,
                airlines
            )
        }

        get ("/{airlinename}"){ //get flights by name
            val airlinename = call.parameters["airlinename"]?:return@get call.respondText(
                "Missing or malformed airlinename", status = HttpStatusCode.BadRequest
            )
            val airline = userCollection.findOne(Airline::name eq airlinename)
            if (airline !== null) {
                call.respond(airline)
            } else {
                call.respondText("User not found" ,status = HttpStatusCode.NotFound)
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
}
