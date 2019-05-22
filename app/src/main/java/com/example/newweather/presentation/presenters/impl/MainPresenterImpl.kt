package com.example.newweather.presentation.presenters.impl

import android.util.Log
import com.bumptech.glide.Glide
import com.example.newweather.domain.interactors.Interactor
import com.example.newweather.domain.models.City
import com.example.newweather.domain.models.CityList
import com.example.newweather.domain.models.CityListView
import com.example.newweather.domain.models.CityView
import com.example.newweather.presentation.presenters.MainPresenter
import com.example.newweather.presentation.ui.activities.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MainPresenterImpl(val interactor: Interactor, val mainActivity: MainActivity): MainPresenter {


    var cityModel: CityView? = null
    lateinit var cityModelList: CityListView

    override fun getData(nameCity: String){
        Log.d("TAG", "Here in Presenter")
        val answer = interactor.getCityModel(nameCity)

        answer.enqueue(object : Callback<City> {
            override fun onResponse(call: Call<City>, response: Response<City>) {
                if(response.isSuccessful){
                    val city = response.body()
                        //city?.main!!.temp = city?.main!!.temp.toInt().toFloat()
                    Log.d("TAG", "Here in ONRESPONSE " + (city?.main!!.temp.toInt()))
                    cityModel = CityView(city?.cityName.toString(),
                        city?.main!!.temp.toInt().toString(),
                        city.wind.speed.toString(),
                        city.main.humidity.toString(),
                        city.main.pressure.toString(),
                        city.main.maxTemp.toString(),
                        city.main.minTemp.toString(),
                        city.weather[0].icon
                        )
                    mainActivity.showCityDate(cityModel!!)
                }else{
                    val error = response.errorBody()
                    Log.d("TAG", "Here in ELSEONRESPONSE")
                    cityModel = CityView(
                        error.toString(),
                        error.toString(),
                        error.toString(),
                        error.toString(),
                        error.toString(),
                        error.toString(),
                        error.toString(),
                        error.toString()
                    )
                }
            }

            override fun onFailure(call: Call<City>, t: Throwable) {
                val error = answer.request().body()
                Log.d("TAG", "Here in ONFAILURE")
                cityModel = CityView(
                    "Error!",
                    "Error",
                    "Error!",
                    "Error!",
                    "Error!",
                    "Error!",
                    "Error!",
                    "Error"
                )

                Log.d("TAG", error.toString())
            }
        })

        if (cityModel != null) {
            Log.d("TAG", "Here in NULL")
            mainActivity.showCityDate(cityModel!!)
        }
    }


    override fun getDataList(nameCity: String) {
        val answer = interactor.getCityListModel(nameCity)
        val formatDayOfWeek = SimpleDateFormat("d,E")

        answer.enqueue(object : Callback<CityList> {
            override fun onResponse(call: Call<CityList>, response: Response<CityList>) {
                if (response.isSuccessful){
                    val city = response.body()
                    Log.d("TAG", "Here in LIST RESPONSE  "  +"  "+ formatDayOfWeek.format(city?.items!![6].getDate().time))
                    //val cityModelList = mutableListOf<CityListView>()
                    val listView = mutableListOf<CityView>()
                    //cityModelList.add(CityListView(listView))
                    //cityModelList[0].itemsCity[0].temp = 10.toString()
                    //cityModelList.itemsCity[0].temp = 10.toString()

                    var k = 0;
                    while (k < 3){

                        listView.add(k,CityView("tt",
                            city.items[4*k].main.temp.toInt().toString(),
                            city.items[4*k].wind.speed.toString(),
                            city.items[4*k].main.humidity.toString(),
                            city.items[4*k].main.minTemp.toString(),
                            city.items[4*k].main.maxTemp.toString(),city.items[4*k].main.pressure.toString(),
                            city.items[4*k].weather[0].icon)).toString()
                        k++
                        }



                    Log.d("TAG", "Here in LIST RESPONSE 2  " )
                    //cityModelList?.items!![1].temp = city.items[6].main.temp.toString()
                    //cityModelList?.items!![2].temp = city.items[6].main.temp.toString()

                 mainActivity.showCityDateList(listView)
                }else{

                }
            }

            override fun onFailure(call: Call<CityList>, t: Throwable) {
                Log.d("TAG", "Here")
            }
        })
    }

    override fun getCity(): String {
        return interactor.getCity()
    }

}
