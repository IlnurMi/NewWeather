package com.example.newweather.presentation.presenters.impl

import android.util.Log
import com.example.newweather.domain.interactors.Interactor
import com.example.newweather.presentation.presenters.MainPresenter
import com.example.newweather.presentation.presenters.MenuPresenter

class MenuPresenterImpl(val interactor: Interactor): MenuPresenter {

    override fun addCity(nameCity: String) {
        Log.d("TAG", "Here in MENUPresenter ADDCITY")
        interactor.addData(nameCity)
    }

    override fun getCities(nameCity: String): List<String> {
        return interactor.addDataCity(nameCity)
    }
}