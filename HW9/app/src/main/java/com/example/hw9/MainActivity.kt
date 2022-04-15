package com.example.hw9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    //設定跑進度條的進度變數
    private var progressBar2sp = 0

    //建立變數以利後續綁定元件
    private lateinit var btn_start: Button
    private lateinit var ed_height: EditText
    private lateinit var ed_weight: EditText
    private lateinit var tv_weight: TextView
    private lateinit var tv_fat: TextView
    private lateinit var tv_progress: TextView
    private lateinit var progressBar2: ProgressBar
    private lateinit var ll_progress: LinearLayout
    private lateinit var btn_boy: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //將變數與 XML 元件綁定
        btn_start = findViewById(R.id.btn_calculate)  //計算按鈕
        ed_height = findViewById(R.id.ed_height)  //輸入的身高
        ed_weight = findViewById(R.id.ed_weight)  //輸入的體重
        tv_weight = findViewById(R.id.tv_weight)  //顯示標準重量
        tv_fat = findViewById(R.id.tv_fat)  //顯示體脂肪
        tv_progress = findViewById(R.id.tv_progress)  //顯示%數
        progressBar2 = findViewById(R.id.progressBar2) //顯示進度橫條
        ll_progress = findViewById(R.id.ll_progress)  //用於顯示元件
        btn_boy = findViewById(R.id.btn_boy)  //性別選取

        //按下按鈕的監聽器
        btn_start.setOnClickListener {
            var msg = Message()
            when {
                ed_height.length() < 1 -> msg.what = 1 //如果沒有輸入身高
                ed_weight.length() < 1 -> msg.what = 2 //如果沒有輸入體重
                else->{//如果輸入資料完整則執行runprogressBar()方法
                    runprogressBar()}
            }
            handler.sendMessage(msg)
        }
    }

    //建立 showToast 方法顯示 Toast 訊息
    private fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()

    //建立 Handler 物件接收訊息
    private val handler = Handler(Looper.getMainLooper()) { msg ->
        if (msg.what == 1)
            showToast("請輸入身高")
        if (msg.what == 2)
            showToast("請輸入體重")
        if (msg.what == 4) {
            ll_progress.visibility = View.GONE //隱藏顯示條(因為已經載入完成)
            val height = ed_height.text.toString().toDouble() //設定身高變數讀取身高
            val weight = ed_weight.text.toString().toDouble() //設定體重變數讀取體重
            val stand_weight: Double
            val stand_bodyfat: Double
            //判斷男女體脂率並分別計算
            if (btn_boy.isChecked) {
                stand_weight = (height - 80)* 0.7
                stand_bodyfat = (weight - 0.88 * stand_weight) / weight * 100
            } else {
                stand_weight = (height - 70)* 0.6
                stand_bodyfat = (weight - 0.82 * stand_weight) / weight * 100
            }
            //設定輸出
            tv_weight.text = "標準體重 \n${String.format("%.2f", stand_weight)}"
            tv_fat.text = "體脂肪 \n${String.format("%.2f", stand_bodyfat)}"
        }
        if (msg.what == 5){
            //執行進度更新
            progressBar2.progress = progressBar2sp
            tv_progress.text = "$progressBar2sp%"
        }
        true
    }

    private fun runprogressBar() {
        ll_progress.visibility = View.VISIBLE
        Thread {
            //初始化橫向進度條、顯示%數、進度
            progressBar2.progress = 0
            tv_progress.text = "0%"
            progressBar2sp = 0
            //建立迴圈執行一百次共延長五秒
            while (progressBar2sp < 100) {
                //休息50毫秒
                Thread.sleep(50)
                val msg = Message()
                //累加
                progressBar2sp++
                //用於更改顯示內容的訊息發送
                msg.what = 5
                handler.sendMessage(msg)
            }
            //用於載入完顯示的資料的訊息發送
            val msg = Message()
            msg.what = 4
            handler.sendMessage(msg)
        }.start()
    }
}