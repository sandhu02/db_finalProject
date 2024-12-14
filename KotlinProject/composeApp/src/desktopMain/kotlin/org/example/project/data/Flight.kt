package org.example.project.data

data class Flight(
    val flightId:Int,
    val origin:String,
    val destination:String,
    val departureTime:String,
    val arrivalTime:String,
    val passengers:List<Int>
)
