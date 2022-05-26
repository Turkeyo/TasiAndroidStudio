package com.example.midterm202206

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.widget.TextView
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var username: TextView = findViewById(R.id.tv_user)
        val btnlogout: Button = findViewById<Button>(R.id.btn_logout)

        intent?.extras?.let {
            val value1 = it.getString("key1")

            username.text = "username:${it.getString("key1")}\n"

            btnlogout.setOnClickListener {
                finish()
            }
        }

    }
}