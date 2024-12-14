package com.example

import com.example.data.Flight
import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

data class Airline(
    val name:String,
    val country:String,
)


val client: MongoClient = KMongo.createClient("mongodb://localhost:27017/")
val database = client.getDatabase("Airport")

val userCollection = database.getCollection<Airline>(collectionName = "airlines")
val flightCollection = database.getCollection<Flight>(collectionName = "flights")
