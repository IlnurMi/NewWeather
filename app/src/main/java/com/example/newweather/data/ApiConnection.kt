package com.example.newweather.data

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection {

    fun getApiConnection(): Api {
        Log.d("TAG", "Here in APICONECTION")
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val Api = retrofit.create(Api::class.java)
        return Api
    }
}