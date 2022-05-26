package com.example.midterm202206

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity() {
    private lateinit var dbrw: SQLiteDatabase

    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //取得資料庫實體
        dbrw = DatabaseHelper(this).writableDatabase
        // val correct_username: String = "admin"
        val righttext = "OK!"
        val wrongtext = "Wrong username or password!"
        var toastmsg: String = "wrong"
        var username: EditText = findViewById(R.id.Name)
        var password: EditText = findViewById(R.id.Password)
        val btnlogin: Button = findViewById<Button>(R.id.button)
        var tvstatus: TextView = findViewById(R.id.tv_status)
        var sbtn: Button = findViewById<Button>(R.id.sbtn)
        var clbtn: Button = findViewById(R.id.clbtn)

        //清除資料庫內容
        clbtn.setOnClickListener {
            dbrw.execSQL("DELETE FROM user")
            Toast.makeText(this@MainActivity, "清除成功", Toast.LENGTH_LONG).show()
        }

        btnlogin.setOnClickListener {
            //顯示錯誤訊息
            tvstatus.text = ""
            //判斷帳號or密碼沒有輸入
            if (username.length() < 1 || password.length() < 1) {
                Toast.makeText(this, "帳號或密碼沒輸入", Toast.LENGTH_LONG).show()
            }else {
                //從資料庫抓取資料
                val queryString = "SELECT * FROM user WHERE name LIKE '${username.text}'"
                val c = dbrw.rawQuery(queryString, null)
                c.moveToFirst() //從第一筆開始輸出
                //如果資料庫沒抓到資料
                if (c.count == 0){
                    tvstatus.text = "wrong username or password"
                }else {
                    //取得資料庫內的帳號
                    val dbName = c.getString(1).toString()
                    //取得資料庫內的密碼
                    val dbPassword = c.getString(2).toString()
                    //如果帳號和密碼正確
                    if (password.text.toString() == dbPassword) {
                        //將欄位清空
                        username.text.clear()
                        password.text.clear()
                        //跳轉畫面
                        val intent = Intent(this, MainActivity2::class.java)
                        val bundle = Bundle()
                        bundle.putString("key1", "${dbName}")
                        intent.putExtras(bundle)
                        startActivity(intent)
                        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
                        //如果帳號密碼錯誤
                    } else {
                        tvstatus.text = "wrong password"
                    }
                    //關閉資料庫
                    c.close()
                }
            }
        }
        sbtn.setOnClickListener {
            username.text.clear()
            password.text.clear()
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }
}