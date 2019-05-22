package com.example.newweather.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "contactDb"
        const val T_CITYS = "contacts"
        const val KEY_ID = "_id"
        const val  KEY_NAME = "name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(
                "create table " + T_CITYS + "(" + KEY_ID
                        + " integer primary key," + KEY_NAME + " text," + " text" + ")"
            )
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            db.execSQL("drop table if exists $T_CITYS")
        }

        onCreate(db)
    }

}