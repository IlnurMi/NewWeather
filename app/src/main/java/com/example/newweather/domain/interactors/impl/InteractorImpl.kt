package com.example.newweather.domain.interactors.impl

import retrofit2.Call
import android.util.Log
import com.example.newweather.domain.interactors.Interactor
import com.example.newweather.domain.models.City
import com.example.newweather.domain.models.CityList
import com.example.newweather.domain.repository.Repository

class InteractorImpl(val repository: Repository): Interactor {


    override fun getCityModel(nameCity: String): Call<City> {
        Log.d("TAG", "Here in Interactor")
        return repository.getWeather(nameCity)
    }

    override fun getCityListModel(nameCity: String): Call<CityList> {
        return repository.getWeatherList(nameCity)
    }

    override fun addData(nameCity: String) {
        repository.addDateCity(nameCity)
    }

    override fun addDataCity(nameCity: String): List<String> {
        return repository.addDateCityList(nameCity)
    }

    override fun getCity(): String {
        return repository.getCity()
    }
}