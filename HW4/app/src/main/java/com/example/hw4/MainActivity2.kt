package com.example.hw4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //判斷Intent不為空，檢查是否夾帶資料
        val tx = findViewById<TextView>(R.id.tx)
        val tx1 = findViewById<TextView>(R.id.tx1)
        intent?.extras?.let{
            //以key找出相對應資料並且取出
            val value = it.getInt("key1")
            val value2 = it.getString("key2")
            tx.text = "姓名為${value2}"
            tx1.text = "年齡為${value}"
        }
    }
}