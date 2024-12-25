package com.example.data

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Payment(
    @BsonId val _id : ObjectId = ObjectId(),
    val amount : Int,
    val date : String,
    val time : String
)