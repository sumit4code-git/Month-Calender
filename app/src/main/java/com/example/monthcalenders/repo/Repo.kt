package com.example.monthcalenders.repo

import com.example.monthcalenders.api.RetroInstancee
import com.example.monthcalenders.models.UsersItem
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repo {
     fun getApiCall(
        onSuccess: (List<UsersItem>) -> Unit,
        onError: (String) -> Unit
    ){
            RetroInstancee.api.getUsers().enqueue(object : Callback<List<UsersItem>> {
            override fun onResponse(
                call: Call<List<UsersItem>>,
                response: Response<List<UsersItem>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    onSuccess(response.body()!!)
                } else if (response.code() == 400) {
                    if (response.errorBody() != null) {
                        onError(JSONObject(response.errorBody()!!.string()).optString("message"))
                    }
                } else {
                    onError("Something went wrong, please try again")
                }
            }

            override fun onFailure(call: Call<List<UsersItem>>, t: Throwable) {
                onError("Something went wrong, please try again")
            }

        })
    }

}