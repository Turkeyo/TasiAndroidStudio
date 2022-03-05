package com.example.hw4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn)
        val ed_name = findViewById<EditText>(R.id.ed_name)
        val ed_age = findViewById<EditText>(R.id.ed_age)
        //按鈕事件
        btn.setOnClickListener {
            //宣告Bundle
            val bundle = Bundle()
            //設定int內容在bundle
            bundle.putInt("key1", 123)
            //設定string內容在bundle
            bundle.putString("key2",ed_name.text.toString())
            //設定第二視窗
            val intent = Intent(this, MainActivity2::class.java)
            //藉由Intent帶到第二視窗
            intent.putExtras(bundle)
            //開始動作
            startActivity(intent)
        }
    }
}