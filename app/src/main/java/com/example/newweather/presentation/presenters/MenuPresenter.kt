package com.example.newweather.presentation.presenters

interface MenuPresenter {
    fun addCity(nameCity: String)
    fun getCities(nameCity: String): List<String>

    interface View{
    }
}