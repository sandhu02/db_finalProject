package org.example.project.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.data.Flight
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchFlight : RetrofitCall () {
    var flightslist by mutableStateOf<List<Flight>?>(null)
    var oneflight by mutableStateOf<Flight?>(null)

    fun fetchFlightsList() {
        val getflights = apiRequest.getFlights()
        getflights.enqueue(object : Callback<List<Flight>> {
            override fun onResponse(call: Call<List<Flight>>, response: retrofit2.Response<List<Flight>>) {
                if (response.isSuccessful) {
                    flightslist = response.body()
                }
                else {
                    flightslist = null
                }
            }

            override fun onFailure(call: Call<List<Flight>>, t: Throwable) {
                flightslist = null
            }
        })
    }

    fun fetchflightsfilterbyCity(origin:String, destination:String) {
        val getflights = apiRequest.getFlightsbyCities(origin, destination)
        getflights.enqueue(object : Callback<List<Flight>> {
            override fun onResponse(call: Call<List<Flight>>, response: retrofit2.Response<List<Flight>>) {
                if (response.isSuccessful) {
                    flightslist = response.body()
                }
                else {
                    flightslist = null
                }
            }

            override fun onFailure(call: Call<List<Flight>>, t: Throwable) {
                flightslist = null
            }
        })
    }

    fun fetchOneFlight(flightId: Int)  {
        val getflight = apiRequest.getFlightbyId(flightId)
        getflight.enqueue(object : Callback<Flight> {
            override fun onResponse(call: Call<Flight>, response: Response<Flight>) {
                if (response.isSuccessful) {
                    oneflight = response.body()
                }
                else {
                    oneflight = null
                }
            }
            override fun onFailure(call: Call<Flight>, t: Throwable) {
                oneflight = null
            }
        })
    }
}