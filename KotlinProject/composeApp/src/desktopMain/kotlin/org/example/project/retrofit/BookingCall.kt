package org.example.project.retrofit

import org.example.project.data.Booking
import org.example.project.data.BookingPaymentRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingCall : RetrofitCall() {


    fun addbooking(bookingPaymentRequest: BookingPaymentRequest) {
        apiRequest.addBooking(bookingPaymentRequest).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    println(response.body())
                }
                else {
                    println(response.errorBody())
                }
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}