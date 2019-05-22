package com.example.newweather.presentation.presenters

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.widget.ImageView
import com.example.newweather.domain.models.CityView

interface MainPresenter {

    fun getData(nameCity: String)
    fun getDataList(nameCity: String)
    fun getCity(): String
    fun getIcon(iconCode: String) : Int

    interface View{
        fun showCityDate(cityModel: CityView)
        fun showCityDateList(listView: List<CityView>)
    }
}