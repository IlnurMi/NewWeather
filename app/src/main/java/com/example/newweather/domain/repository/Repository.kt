package com.example.newweather.domain.repository

import com.example.newweather.domain.models.City
import com.example.newweather.domain.models.CityList
import retrofit2.Call

interface Repository {
    fun getWeather(nameCity: String): Call<City>
    fun getWeatherList(nameCity: String): Call<CityList>
    fun addDateCity(nameCity: String)
    fun addDateCityList(nameCity: String): List<String>
    fun getCity(): String
}