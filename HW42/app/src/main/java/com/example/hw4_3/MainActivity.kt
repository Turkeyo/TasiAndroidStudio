package com.example.hw4_3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let {
            if(requestCode==1 && resultCode == Activity.RESULT_OK){
                val value_drink = it.getString("drinkname")
                val a1_drink = findViewById<TextView>(R.id.a1_drink)
                a1_drink.text = "飲料：${value_drink}"

                val value_suger = it.getString("suger")
                val a1_suger = findViewById<TextView>(R.id.a1_suger)
                a1_suger.text = " 甜度：${value_suger}"

                val value_ice = it.getString("ice")
                val a1_ice = findViewById<TextView>(R.id.a1_ice)
                a1_ice.text = "冰度：${value_ice}"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val a1_order_btn = findViewById<Button>(R.id.a1_order_btn)
        val bundle = Bundle()
        a1_order_btn.setOnClickListener{
            bundle.putString("order","A")
            val i = Intent(this,MainActivity2::class.java)
            i.putExtras(bundle)
            @Suppress("DEPRECATION")
            startActivityForResult(i,1)
        }
    }
}