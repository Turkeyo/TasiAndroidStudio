package com.example.hw4_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //判斷資料不為空
        intent?.extras?.let {
            val a2_btn = findViewById<Button>(R.id.a2_btn)
            val valueS = it.getString("KeyS")
            val bundle = Bundle()
            a2_btn.setOnClickListener {
                bundle.putString("A2_Key", "歡迎 ${valueS}")
                val intent = Intent().putExtras(bundle)
                setResult(Activity.RESULT_OK, intent)
                finish()

            }
        }
    }
}