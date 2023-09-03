package com.example.monthcalenders.api

import com.example.monthcalenders.models.UsersItem
import retrofit2.Call
import retrofit2.http.GET

interface CallAPI {
    @GET("users")
     fun getUsers(
    ): Call<List<UsersItem>>
}