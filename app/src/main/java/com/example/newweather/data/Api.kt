package com.example.newweather.data

import retrofit2.Call
import com.example.newweather.domain.models.City
import com.example.newweather.domain.models.CityList
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("weather")
    fun getCityDate(
        @Query("q") name: String,
        @Query("units") units: String,
        @Query("APPID") appid: String
    ): Call<City>

    @GET("forecast")
    fun getCityListDate(
        @Query("q") name: String,
        @Query("units") units: String,
        @Query("APPID") appid: String
    ): Call<CityList>
}