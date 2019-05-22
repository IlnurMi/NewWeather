package com.example.newweather.presentation.presenters

import com.example.newweather.domain.models.CityView

interface MainPresenter {

    fun getData(nameCity: String)
    fun getDataList(nameCity: String)
    fun getCity(): String


    interface View{
        fun showCityDate(cityModel: CityView)
        fun showCityDateList(listView: List<CityView>)
    }
}