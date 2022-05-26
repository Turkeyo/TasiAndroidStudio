package com.example.midterm202206

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context,
    name: String = database,
    factory: SQLiteDatabase.CursorFactory? = null,
    version: Int = v
) : SQLiteOpenHelper(context, name, factory, version) {
    companion object {
        private const val database = "myDatabase" //資料庫名稱
        private const val v = 1 //資料庫版本
    }
    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL("CREATE TABLE user(name varchar(30),password int(20))")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int,newVersion: Int) {
        //升級資料庫版本時，刪除舊資料表，並重新執行 onCreate()，建立新資料表
        db.execSQL("DROP TABLE IF EXISTS myTable")
        onCreate(db)
    }
}