package com.example.monthcalenders.models


import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat")
    var lat: String? = null,
    @SerializedName("lng")
    var lng: String? = null
)