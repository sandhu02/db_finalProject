package com.example.data

data class BookingPaymentRequest(
    val booking : Booking,
    val payment: Payment
)