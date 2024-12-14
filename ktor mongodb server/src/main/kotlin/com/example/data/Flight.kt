package com.example.data

import org.bson.codecs.pojo.annotations.BsonProperty


data class Flight(
    @BsonProperty("flightId") val flightId:Int,
    @BsonProperty("origin") val origin:String,
    @BsonProperty("destination") val destination:String,
    @BsonProperty("departureTime") val departureTime:String,
    @BsonProperty("arrivalTime") val arrivalTime:String,
    @BsonProperty("passengers") val passengers:List<Int>
)
