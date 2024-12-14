package org.example.project.retrofit

import org.example.project.data.Airline
import org.example.project.data.Flight
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/airlines")
    fun getAirlines() : Call<List<Airline>>

    @GET("/airlines/{airlinename}")
    fun getAirlinebyname(@Path("airlinename") airline: String) : Airline

    @GET("/flights")
    fun getFlights() : Call<List<Flight>>

    @GET("/flights/{flightid}")
    fun getFlightbyId(@Path("flightid") flightid: Int) : Call<Flight>
}