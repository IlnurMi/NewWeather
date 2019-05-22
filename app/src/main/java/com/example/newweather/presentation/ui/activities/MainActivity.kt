package com.example.newweather.presentation.ui.activities

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.newweather.R
import com.example.newweather.data.ApiConnection
import com.example.newweather.data.DBHelper
import com.example.newweather.data.repository.RepositoryImpl
import com.example.newweather.domain.interactors.impl.InteractorImpl
import com.example.newweather.domain.models.CityView
import com.example.newweather.presentation.presenters.MainPresenter
import com.example.newweather.presentation.presenters.impl.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    lateinit var mainPresenter: MainPresenterImpl

    lateinit var mCityTextView: TextView
    lateinit var mTempTextView: TextView
    lateinit var mTempTextView_1: TextView
    lateinit var mTempTextView_2: TextView
    lateinit var mTempTextView_3: TextView

    lateinit var mImageButton: ImageButton
    lateinit var mImage: ImageView
    lateinit var mImage_1: ImageView
    lateinit var mImage_2: ImageView
    lateinit var mImage_3: ImageView
    lateinit var city: String
    var KEY = 0;
    var CITY = "Moscow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mCityTextView = findViewById(R.id.city_textview)
        mTempTextView = findViewById(R.id.temp_textview)
        mTempTextView_1 = findViewById(R.id.temp_1_textview)
        mTempTextView_2 = findViewById(R.id.temp_2_textview)
        mTempTextView_3 = findViewById(R.id.temp_3_textview)
        mImageButton = findViewById(R.id.imageButton)
        mImage = findViewById(R.id.image_view)
        mImage_1 = findViewById(R.id.image_1_view)
        mImage_2 = findViewById(R.id.image_2_view)
        mImage_3 = findViewById(R.id.image_3_view)

        mainPresenter = MainPresenterImpl(InteractorImpl(RepositoryImpl(ApiConnection().getApiConnection(), DBHelper(this))), this)

        city = mainPresenter.getCity()
        Log.d("TAG", "Here in MainActivity Function getCity " + city)

        mainPresenter.getData(CITY)
        mainPresenter.getDataList(CITY)

        mImageButton.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivityForResult(intent,KEY)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == KEY) {
            if (resultCode == Activity.RESULT_OK) {
                val thiefname = data?.getStringExtra("name")
                CITY = thiefname!!

                mainPresenter.getData(CITY)
                mainPresenter.getDataList(CITY)

            }
        }

    }

    override fun showCityDate(cityModel: CityView) {
        image_view.setImageResource(mainPresenter.getIcon(cityModel.icon))
        //image_view.setImageResource(R.drawable.ic_shower_rain)
        //Log.d("TAG", "Here in MainActivity Function ShowCityData  " + cityModel.icon)
        //Glide.with(this).load("http://openweathermap.org/img/w/" + cityModel.icon + ".png").into(image_view)
        mCityTextView.text = cityModel.cityName
        //mTempTextView.setText(cityModel.temp)
        mTempTextView.text = cityModel.temp + " C"
    }

    override fun showCityDateList(listView: List<CityView>) {
        mTempTextView_1.text = listView.get(0).temp + " C"
        mTempTextView_2.text = listView.get(1).temp + " C"
        mTempTextView_3.text = listView.get(2).temp + " C"

        image_1_view.setImageResource(R.drawable.ic_scattered_clouds)
       image_2_view.setImageResource(R.drawable.ic_mist)
        image_3_view.setImageResource(R.drawable.ic_thunderstorm)
        //Glide.with(this).load("http://openweathermap.org/img/w/" + listView.get(0).icon + ".png").into(image_1_view)
        //Glide.with(this).load("http://openweathermap.org/img/w/" + listView.get(1).icon + ".png").into(image_2_view)
        //Glide.with(this).load("http://openweathermap.org/img/w/" + listView.get(2).icon + ".png").into(image_3_view)
        Log.d("TAG", "Here in MainActivity Function ShowCityData 0  " + listView.get(0).icon)
        Log.d("TAG", "Here in MainActivity Function ShowCityData 1  " + listView.get(1).icon)
        Log.d("TAG", "Here in MainActivity Function ShowCityData 2  " + listView.get(2).icon)
    }

}

