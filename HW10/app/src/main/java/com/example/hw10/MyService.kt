package com.example.hw10

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Thread(Runnable
        {
            try {
                //讓程式休息5秒
                Thread.sleep(5000)
                //設定跳轉MainActivity2.kt的變數
                val intent = Intent(this@MyService, MainActivity2::class.java)
                //設定標籤
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                //開始執行
                this@MyService.startActivity(intent)
            } catch (e: InterruptedException) {  //抓取錯誤
                e.printStackTrace()
            }
        }).start()
    }
    override fun onBind(intent: Intent): IBinder? {
        TODO("Return the communication channel to the service.")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return super.onStartCommand(intent, flags, startId)
    }
}