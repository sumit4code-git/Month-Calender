package com.example.monthcalenders.models


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("bs")
    var bs: String? = null,
    @SerializedName("catchPhrase")
    var catchPhrase: String? = null,
    @SerializedName("name")
    var name: String? = null
)