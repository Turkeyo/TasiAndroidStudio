package com.example.hw9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings
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
    private lateinit var ed_age: EditText
    private lateinit var tv_weight: TextView
    private lateinit var tv_fat: TextView
    private lateinit var tv_bmi: TextView
    private lateinit var tv_progress: TextView
    private lateinit var progressBar2: ProgressBar
    private lateinit var ll_progress: LinearLayout
    private lateinit var btn_boy: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //將變數與 XML 元件綁定
        btn_start = findViewById(R.id.btn_calculate)
        ed_height = findViewById(R.id.ed_height)
        ed_weight = findViewById(R.id.ed_weight)
        ed_age = findViewById(R.id.ed_age)
        tv_weight = findViewById(R.id.tv_weight)
        tv_fat = findViewById(R.id.tv_fat)
        tv_bmi = findViewById(R.id.tv_bmi)
        tv_progress = findViewById(R.id.tv_progress)
        progressBar2 = findViewById(R.id.progressBar2)
        ll_progress = findViewById(R.id.ll_progress)
        btn_boy = findViewById(R.id.btn_boy)

        //按下按鈕的監聽器
        btn_start.setOnClickListener {
            var msg = Message()
            when {
                ed_height.length() < 1 -> msg.what = 1 //如果沒有輸入身高
                ed_weight.length() < 1 -> msg.what = 2 //如果沒有輸入體重
                ed_age.length() < 1    -> msg.what = 3 //如果沒有輸入年齡
                else->{ll_progress.visibility = View.VISIBLE //如果輸入資料完整則先顯示進度條後執行runprogressBar()方法
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
        if (msg.what == 3)
            showToast("請輸入年齡")
        if (msg.what == 4) {
            ll_progress.visibility = View.GONE //隱藏顯示條(因為已經載入完成)
            val height = ed_height.text.toString().toDouble() //設定身高變數讀取身高
            val weight = ed_weight.text.toString().toDouble() //設定體重變數讀取體重
            val age = ed_age.text.toString().toDouble() //設定年齡變數讀取年齡
            val bmi = weight / ((height / 100).pow(2)) //BMI計算
            //判斷男女體脂率並分別計算
            val (stand_weight, body_fat) = if (btn_boy.isChecked) {
                Pair((height - 80) * 0.7, 1.39 * bmi + 0.16 * age - 19.34)
            } else {
                Pair((height - 70) * 0.6, 1.39 * bmi + 0.16 * age - 9)
            }
            //設定輸出
            tv_weight.text = "標準體重 \n${String.format("%.2f", stand_weight)}"
            tv_fat.text = "體脂肪 \n${String.format("%.2f", body_fat)}"
            tv_bmi.text = "BMI \n${String.format("%.2f", bmi)}"
        }
        if (msg.what == 5){
            //執行進度更新
            progressBar2.progress = progressBar2sp
            tv_progress.text = "$progressBar2sp%"
        }
        true
    }

    private fun runprogressBar() {
        Thread {
            //初始化橫向進度條及顯示%數
            progressBar2.progress = 0
            tv_progress.text = "0%"
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