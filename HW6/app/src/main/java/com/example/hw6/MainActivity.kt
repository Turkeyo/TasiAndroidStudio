@file:Suppress("DEPRECATION")

package com.example.hw6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<Button>(R.id.btn)
        var btn2 = findViewById<Button>(R.id.btn2)
        var btn3 = findViewById<Button>(R.id.btn3)
        var btn4 = findViewById<Button>(R.id.btn4)
        var btn5 = findViewById<Button>(R.id.btn5)
        btn.setOnClickListener {
            Toast.makeText(this,"預設TOAST",Toast.LENGTH_SHORT).show()
        }
        btn2.setOnClickListener {
            val toast = Toast(this)
            toast.setGravity(Gravity.TOP, 0, 50)
            toast.duration = Toast.LENGTH_SHORT
            toast.view = layoutInflater.inflate(R.layout.toast_custom, null)

            toast.show()
        }
        btn3.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("按鍵式對話框")
                .setMessage("對話內容")
                .setNeutralButton("取消") { dialog, which ->
                    Toast.makeText(this,"取消", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("拒絕") { dialog, which ->
                    Toast.makeText(this, "拒絕", Toast.LENGTH_SHORT).show()
                }
                .setPositiveButton("確定") { dialog, which ->
                    Toast.makeText(this, "確定", Toast.LENGTH_SHORT).show()
                }.show()
        }
        btn4.setOnClickListener{
            val list_item = arrayOf("對話框選項1","對話框選項2","對話框選項3","對話框選項4","對話框選項5")
            AlertDialog.Builder(this)
                .setTitle("列表式對話框")
                .setItems(list_item){ dialogInterface, i ->
                    Toast.makeText(this, "你選的是" + list_item[i], Toast.LENGTH_SHORT).show()
                }.show()
        }
        btn5.setOnClickListener{
            val list_item = arrayOf("對話框選項1","對話框選項2","對話框選項3","對話框選項4","對話框選項5")
            var position = 0
            AlertDialog.Builder(this)
                .setTitle("單選式對話框")
                .setSingleChoiceItems(list_item, 0){ dialogInterface, i ->
                    position = i
                }
                .setPositiveButton("確定") { dialog, which ->
                    Toast.makeText(this,"你選的是" + list_item[position],
                        Toast.LENGTH_SHORT).show()
                }.show()
        }
    }
}