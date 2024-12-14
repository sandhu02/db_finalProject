package org.example.project.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.data.Airline
import retrofit2.Call
import retrofit2.Callback

class FetchAirline : RetrofitCall(){
    var fetchedData by mutableStateOf<List<Airline>?>(null)

    var responseSuccess by mutableStateOf("not yet initialized")

    fun fetchData() {
        val retrofitData = apiRequest.getAirlines()
        retrofitData.enqueue(object : Callback<List<Airline>> {
            override fun onResponse(call: Call<List<Airline>>, response: retrofit2.Response<List<Airline>>) {
                if (response.isSuccessful) {
                    fetchedData = response.body()
                }
                else {
                    fetchedData = null
                    responseSuccess = "Response Failure"
                }
            }
            override fun onFailure(call: Call<List<Airline>>, t: Throwable) {
                fetchedData = null
                responseSuccess = "Failed to fetch data"
            }
        })
    }
}