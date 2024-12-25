package com.example.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Airline(
    val name: String,
    val country: String,
    val airlineId : Int
)
