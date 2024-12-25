package com.example.data

import org.bson.codecs.pojo.annotations.BsonProperty

data class User(
    @BsonProperty("username") val username: String,
    @BsonProperty("password") val password: String,
    @BsonProperty("name") val name: String
)
