package com.example.newweather.data.repository

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import retrofit2.Call
import android.util.Log
import com.example.newweather.data.Api
import com.example.newweather.data.DBHelper
import com.example.newweather.domain.models.City
import com.example.newweather.domain.models.CityList
import com.example.newweather.domain.repository.Repository

class RepositoryImpl(val client: Api, val dbHelper: DBHelper): Repository {

    override fun getWeather(nameCity: String): Call<City> {
        Log.d("TAG", "Here in Repository")
        return client.getCityDate(nameCity,"metric" ,"fd6364786de34099eb9ee2121b1e39c1")
    }

    override fun getWeatherList(nameCity: String): Call<CityList> {
        return client.getCityListDate(nameCity,"metric","fd6364786de34099eb9ee2121b1e39c1")
    }

    override fun addDateCity(nameCity: String) {
        Log.d("TAG", "Here in Repository ADDCITY")
        val database = dbHelper.getWritableDatabase()
        val contentValues = ContentValues()
        //database.delete("T_CTYS", null, null)
        contentValues.put(DBHelper.KEY_NAME, nameCity)
        database.insert(DBHelper.T_CITYS, null, contentValues)

        dbHelper.close()
    }

    override fun addDateCityList(nameCity: String): List<String> {
        var city_list = mutableListOf<String>()
        val database = dbHelper.getWritableDatabase()
        val cursor = database.query(DBHelper.T_CITYS,null,null,null,null,null,null)

        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME)
            do {
                city_list.add(cursor.getString(nameIndex))
                Log.d("TAG", "Here in Repository ADDCITYNAME" + cursor.getString(nameIndex))
                cursor.getString(nameIndex)
            } while (cursor.moveToNext())
        }

        return city_list
    }

    override fun getCity(): String {
        var city = ""
        val database = dbHelper.getWritableDatabase()
        val cursor = database.query(DBHelper.T_CITYS,null,null,null,null,null,null)

        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME)
            do {
                city = cursor.getString(nameIndex)
                Log.d("TAG", "Here in Repository Function getCity " + city)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return city
    }
}