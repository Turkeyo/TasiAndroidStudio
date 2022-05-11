package com.example.hw11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var flag = false
    lateinit var tv_clock: TextView
    lateinit var btn: Button
    //建立BroadcastReceiver物件
    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        //在onReceive()中加入接收廣播後要執行的動作
        override fun onReceive(context: Context, intent: Intent) {
            //解析Intent取得秒數資訊
            intent.extras?.let {
                tv_clock?.text = "%02d:%02d:%02d".format(it.getInt("H"), it.getInt("M"), it.getInt("S"))
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_clock = findViewById(R.id.tv_clock)
        btn = findViewById(R.id.button)
        //建立IntentFilter物件指定要接收的識別字串MyMessage
        val intentfilter = IntentFilter("MyMessage")
        //註冊Receiver狀態
        registerReceiver(receiver, intentfilter)
        //取得Service狀態
        flag = MyService.flag
        btn.text = if(flag) "暫停" else "開始"

        btn.setOnClickListener{
            flag = !flag
            btn.text = if(flag) "暫停" else "開始"
            //啟動Service
            startService(Intent(this, MyService::class.java).putExtra("flag",flag))
            Toast.makeText(this, if(flag) "計時開始" else "計時暫停", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}