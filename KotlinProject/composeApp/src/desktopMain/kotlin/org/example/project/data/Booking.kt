package org.example.project.data

data class Booking(
    val flightId: Int,
    val seatNumber: Int,
    val bookingDate: String,
    val paymentId: ObjectId? = null,
    val username: String,
)