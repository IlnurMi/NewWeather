package com.example.newweather.domain.models

import com.google.gson.annotations.SerializedName
import java.util.*


class City(
    @SerializedName("main")
    val main: Main,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("dt")
    val timestamp: Long){

    fun getDate(): Calendar {
        val date = Calendar.getInstance()
        date.timeInMillis = timestamp * 1000
        return date
    }
}