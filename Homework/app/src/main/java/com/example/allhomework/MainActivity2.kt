package com.example.allhomework

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity2 : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    //設定顯示文字
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    //設定儲存時間
    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //設定取得時間的方法
        pickDate()
        //設定返回按鈕
        val BtnBack = findViewById<Button>(R.id.BtnBack)
        val bundle = Bundle()
        BtnBack.setOnClickListener{
            //傳回訊息
            setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
            finish()
        }
    }

    private fun getDateTimeCalenar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)

    }

    private fun pickDate(){
        val btn_timePicker = findViewById<Button>(R.id.btn_timePicker)
        btn_timePicker.setOnClickListener{
            getDateTimeCalenar()

            DatePickerDialog(this,this,year,month,day).show()
        }
    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month+1
        saveYear = year

        getDateTimeCalenar()
        TimePickerDialog(this,this,hour,minute,true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        saveHour = p1
        saveMinute = p2

        val tv_textTime = findViewById<TextView>(R.id.tv_textTime)
        tv_textTime.text = "$saveDay-$saveMonth-$saveYear \n Hour： $saveHour Minute： $saveMinute"
    }
}