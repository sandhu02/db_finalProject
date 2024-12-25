package org.example.project.retrofit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.example.project.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchUser : RetrofitCall(){
    var oneUser by mutableStateOf<User?>(null)

    fun fetchOneUser(username: String) {
        val getUser = apiRequest.getUser(username)
        getUser.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    oneUser = response.body()
                }
                else {
                    oneUser = null
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                oneUser = null
            }
        })
    }

    fun insertOneUser(user: User) {
        apiRequest.insertUser(user).enqueue(object : Callback<String>{
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

