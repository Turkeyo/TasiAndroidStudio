package com.example.midterm202206

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {
    private lateinit var dbrw: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var username1: EditText = findViewById(R.id.Username1)
        var password1: EditText = findViewById(R.id.Password1)
        var signupbtn: Button = findViewById<Button>(R.id.Signupbtn)
        dbrw = DatabaseHelper(this).writableDatabase

        //username.text = "username:${it.getString("key2")}\n"
        signupbtn.setOnClickListener {
            try{
                dbrw.execSQL(
                    "INSERT INTO user(name,password) VALUES(?,?)",
                    arrayOf(username1.text.toString(),
                        password1.text.toString())
                )
                showToast("新增成功")
                cleanEditText()
            }catch (e: Exception) {
                showToast("新增失敗:$e")
            }
            finish()
        }
    }
    private fun showToast(text: String) =
        Toast.makeText(this,text, Toast.LENGTH_LONG).show()
    private fun cleanEditText() {
        findViewById<EditText>(R.id.Username1).setText("")
        findViewById<EditText>(R.id.Password1).setText("")
    }
}