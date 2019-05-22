package com.example.newweather.domain.models

import com.google.gson.annotations.SerializedName

class Main(
    @SerializedName("temp")
    var temp: Float,
    val pressure: Float,
    val humidity: Float,
    @SerializedName("temp_min")
    val minTemp: Float,
    @SerializedName("temp_max")
    val maxTemp: Float)