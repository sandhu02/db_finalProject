package org.example.project.retrofit

import org.example.project.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {
    @GET("/airlines")
    fun getAirlines() : Call<List<Airline>>

    @GET("/airlines/{airlinename}")
    fun getAirlinebyname(@Path("airlinename") airline: String) : Airline

    @GET("/airlines/{airlineId}")
    fun getAirlinebyId(@Path("airlineId") airlineId: Int ) : Call<Airline>

    @GET("/flights")
    fun getFlights() : Call<List<Flight>>

    @GET("/flights/{flightid}")
    fun getFlightbyId(@Path("flightid") flightid: Int) : Call<Flight>

    @GET("/flights/{origin}/{destination}")
    fun getFlightsbyCities(@Path("origin") origin: String, @Path("destination") destination: String) : Call<List<Flight>>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String) : Call<User>

    @POST("/users/{user}")
    fun insertUser(@Body user : User) : Call<String>

    @POST("/bookings/{bookingPayment}")
    fun addBooking( @Body bookingPaymentRequest : BookingPaymentRequest ) : Call<String>

}
