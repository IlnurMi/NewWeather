package com.example.newweather.domain.interactors

import retrofit2.Call
import com.example.newweather.domain.models.City
import com.example.newweather.domain.models.CityList

interface Interactor {
    fun getCityModel(nameCity: String): Call<City>
    fun getCityListModel(nameCity: String): Call<CityList>
    fun addData(nameCity: String)
    fun  addDataCity(nameCity: String): List<String>
    fun getCity(): String
}