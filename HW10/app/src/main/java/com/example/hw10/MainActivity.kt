package com.example.hw10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //取得按鈕元素
        var btn = findViewById<Button>(R.id.btn)

        //按鈕觸發事件
        btn.setOnClickListener{
            //執行MyService.kt
            startService(Intent(this,MyService::class.java))
            //顯示訊息
            Toast.makeText(this,"啟動Service",Toast.LENGTH_SHORT)
            //關閉程式
            finish()
        }
    }
}