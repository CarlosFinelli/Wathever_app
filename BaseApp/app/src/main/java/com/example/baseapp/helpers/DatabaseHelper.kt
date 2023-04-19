package com.example.baseapp.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.baseapp.classes.Photos

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, "MyDatabase.db", null, 1) {
    companion object {
        val TABLE_PHOTOS = "photos"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePhotos = "CREATE TABLE $TABLE_PHOTOS (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Path TEXT)"

        db?.execSQL(createTablePhotos)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_PHOTOS")

        onCreate(db)
    }

    fun selectPhotos() : ArrayList<Photos> {
        val arrayList = ArrayList<Photos>()

        return arrayList
    }
}