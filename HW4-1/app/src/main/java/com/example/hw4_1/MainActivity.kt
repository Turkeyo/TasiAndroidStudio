package com.example.hw4_1

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //回傳系統
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //判斷回傳資料不為空
        data?.extras?.let {
            //驗證發出對象
            if(requestCode==1 && resultCode== Activity.RESULT_OK){
                //取得key 為A2_key的內容
                val valueS = it.getString("A2_Key")
                //取得第一視窗的顯示框
                val a1_tx_view = findViewById<TextView>(R.id.a1_tx_view)
                //更改內容
                a1_tx_view.text = valueS.toString()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //設定按鈕變數
        val a1_btn = findViewById<Button>(R.id.a1_btn)
        //設定輸入欄位變數
        val a1_ed_name = findViewById<EditText>(R.id.a1_ed_name)
        //設定輸出到第二視窗的變數
        val bundle = Bundle()
        //按鈕事件
        a1_btn.setOnClickListener{
            //加入 keyS 內容為輸入欄位內容 到 bundle
            bundle.putString("KeyS",a1_ed_name.text.toString())
            //設定第二視窗
            val i = Intent(this,MainActivity2::class.java)
            //將bundle 資料傳入到第二視窗
            i.putExtras(bundle)
            //使用拋棄方法
            @Suppress("DEPRECATION")
            //開始執行傳入到第二視窗方法
            startActivityForResult(i,1)
        }
    }
}
