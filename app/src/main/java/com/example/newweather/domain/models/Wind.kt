package com.example.newweather.domain.models

import com.google.gson.annotations.SerializedName

class Wind(
    @SerializedName("speed")
    val speed: Float,
    @SerializedName("deg")
    val degree: Float)