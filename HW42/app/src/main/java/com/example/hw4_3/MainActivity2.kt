package com.example.hw4_3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val a2_go =  findViewById<Button>(R.id.a2_go)
        val a2_suger_group = findViewById<RadioGroup>(R.id.a2_suger_group)
        val a2_ice_group = findViewById<RadioGroup>(R.id.a2_ice_group)
        val a2_drinkname = findViewById<EditText>(R.id.a2_drinkname)
        intent?.extras.let {
            val bundle = Bundle()
            a2_go.setOnClickListener{
                if(a2_drinkname.length() <1)
                    Toast.makeText(this,"請輸入飲料名稱",Toast.LENGTH_SHORT).show()
                else {
                    bundle.putString("suger", a2_suger_group.findViewById<RadioButton>(a2_suger_group.checkedRadioButtonId).text.toString())
                    bundle.putString("ice", a2_ice_group.findViewById<RadioButton>(a2_ice_group.checkedRadioButtonId).text.toString())
                    bundle.putString("drinkname", a2_drinkname.text.toString())
                    setResult(Activity.RESULT_OK, Intent().putExtras(bundle))
                    finish()
                }
            }
        }
    }
}