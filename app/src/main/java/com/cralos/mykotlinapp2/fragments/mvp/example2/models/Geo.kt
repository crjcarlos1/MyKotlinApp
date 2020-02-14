package com.cralos.mykotlinapp2.fragments.mvp.example2.models

import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)