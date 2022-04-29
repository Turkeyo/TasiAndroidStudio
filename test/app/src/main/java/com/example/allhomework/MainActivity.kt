package com.example.allhomework

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var username = findViewById<EditText>(R.id.username)
        var password = findViewById<EditText>(R.id.password)
        var btnLogin = findViewById<Button>(R.id.btn)
        var correct_username = "aaa"
        var correct_password = "123"

        btnLogin.setOnClickListener {
            if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                Toast.makeText(this,"輸入資料不完整",Toast.LENGTH_LONG).show();
            }else if(username.getText().toString().equals(correct_username)){
                if(password.getText().toString().equals(correct_password)){
                    Toast.makeText(this,"登入成功",Toast.LENGTH_LONG).show();
                    val bundle = Bundle()
                    bundle.putString("username","${username.text}")
                    val i = Intent(this, MainActivity2::class.java)
                    i.putExtras(bundle)
                    startActivityForResult(i, 1)
                }else{
                    Toast.makeText(this,"密碼錯誤",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this,"帳號或密碼錯誤",Toast.LENGTH_LONG).show();
            }
        }
    }
}