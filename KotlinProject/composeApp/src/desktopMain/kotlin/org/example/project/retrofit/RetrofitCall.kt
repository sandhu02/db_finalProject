package org.example.project.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.data.Airline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class RetrofitCall {
    val baseUrl = "http://127.0.0.1:8080/"

    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiRequest: ApiService = retrofit.create(ApiService::class.java)
}