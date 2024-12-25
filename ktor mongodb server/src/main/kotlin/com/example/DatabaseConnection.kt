package com.exampleu

import com.example.data.*
import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection


val client: MongoClient = KMongo.createClient("mongodb://localhost:27017/")
val database = client.getDatabase("Airport")

val airlineCollection = database.getCollection<Airline>(collectionName = "airlines")
val flightCollection = database.getCollection<Flight>(collectionName = "flights")
val userCollection = database.getCollection<User>(collectionName = "users")
val bookingCollection = database.getCollection<Booking>(collectionName = "bookings")
val paymentCollection = database.getCollection<Payment>(collectionName = "payments")