package com.example.newweather.presentation.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewConfiguration
import android.widget.*
import com.example.newweather.R
import com.example.newweather.data.ApiConnection
import com.example.newweather.data.DBHelper
import com.example.newweather.data.repository.RepositoryImpl
import com.example.newweather.domain.interactors.impl.InteractorImpl
import com.example.newweather.presentation.presenters.MenuPresenter
import com.example.newweather.presentation.presenters.impl.MenuPresenterImpl

class MenuActivity : AppCompatActivity(), MenuPresenter.View {

    lateinit var addButton: ImageButton
    lateinit var addText: EditText
    lateinit var scrolNes: ScrollView
    lateinit var list_layout: LinearLayout

    lateinit var man_layout: LinearLayout

    var nameCity: String = ""
    val THIEF = "THIEF"
    lateinit var menuPresenter: MenuPresenterImpl


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        addButton = findViewById(R.id.imageButton_add)
        addText = findViewById(R.id.edit_text)
        man_layout = findViewById(R.id.main_layout)

        menuPresenter = MenuPresenterImpl(InteractorImpl(RepositoryImpl(ApiConnection().getApiConnection(), DBHelper(this))))

        //menuPresenter.addCity(nameCity)

        //val array_list = menuPresenter.getCities(nameCity)

        for (name in menuPresenter.getCities(nameCity)) {
                val view = layoutInflater.inflate(R.layout.shablon_activity, null)
                val textView = view.findViewById<TextView>(R.id.shablon_city)
                textView.text = name
                man_layout.addView(view)
                Log.d("TAG", "Here ARRAY_LIST_SIZE" + name)
        }

        addButton.setOnClickListener{
            Log.d("TAG", "Here in ADDBUTTON Click")
            nameCity = addText.text.toString()

            if(nameCity != "") {
                menuPresenter.addCity(nameCity)
            }

            val answerIntent = Intent()
            answerIntent.putExtra("name", addText.getText().toString())
            setResult(Activity.RESULT_OK, answerIntent)
            finish()
        }
    }


    fun onClick(view: View){
        /*val answerIntent = Intent()
        val text = view.findViewById<TextView>(R.id.shablon_city)
        answerIntent.putExtra(THIEF, text.getText().toString())
        Log.d("mLog", text.getText().toString())
        setResult(Activity.RESULT_OK, answerIntent)
        finish()*/
        val answerIntent = Intent()
        val text = view.findViewById<TextView>(R.id.shablon_city)
        Log.d("mLog", "Here in MenuActivity Function onClick TEXT " + text.text)
        answerIntent.putExtra("name", text.text)
        setResult(Activity.RESULT_OK, answerIntent)
        finish()
    }

}




