package org.example.project.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.data.Airline
import org.example.project.data.ObjectId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchAirline : RetrofitCall(){
    var fetchedData by mutableStateOf<List<Airline>?>(null)

    var fetchedAirline by mutableStateOf<Airline?>(null)

    fun fetchData() {
        val retrofitData = apiRequest.getAirlines()
        retrofitData.enqueue(object : Callback<List<Airline>> {
            override fun onResponse(call: Call<List<Airline>>, response: Response<List<Airline>>) {
                if (response.isSuccessful) {
                    fetchedData = response.body()
                }
                else {
                    fetchedData = null
                }
            }
            override fun onFailure(call: Call<List<Airline>>, t: Throwable) {
                fetchedData = null
            }
        })
    }

    fun getAirlinebyId(airlineId: Int) {
        val getAirline = apiRequest.getAirlinebyId(airlineId = airlineId)
        getAirline.enqueue(object : Callback<Airline> {
            override fun onResponse(call: Call<Airline>, response: Response<Airline>) {
                if (response.isSuccessful) {
                    fetchedAirline = response.body()
                }
                else {
                    fetchedAirline = null
                }
            }
            override fun onFailure(call: Call<Airline>, t: Throwable) {
                fetchedAirline = null
            }
        })
    }
}