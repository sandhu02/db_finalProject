package com.example.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.types.ObjectId

data class Booking(
    val flightId: Int,
    val seatNumber: Int,
    val bookingDate: String,
    val paymentId: ObjectId = ObjectId(),
    val username: String,
)