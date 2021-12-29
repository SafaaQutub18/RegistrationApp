package com.safaa.registrationapp.services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.safaa.registrationapp.Model.User


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,"registration.dp",null,1) {
    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if(db!=null)
            db.execSQL("create table users(Name text,Phone text,Location text,Password text )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun saveData(user:User){
        val contentValues = ContentValues()
        contentValues.put("Name", user.name)
        contentValues.put("Location", user.location)
        contentValues.put("Phone", user.phone)
        contentValues.put("Password", user.password)
        sqLiteDatabase.insert("users",null,contentValues)
        Log.d("DBHelper", user.name)
    }

    fun readUserData(userName :String): User?{
        var user : User? = null
        //read  data using cursor var
        val cursor : Cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE Name LIKE '$userName'", null)

        if(cursor.count < 1)
            print("No data found")
        else {
            cursor.moveToFirst()
            user = User(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3))
        }
        return user
    }
}