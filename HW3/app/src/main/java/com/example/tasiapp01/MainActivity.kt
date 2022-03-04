package com.example.tasiapp01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //設定變數
        val ed_name = findViewById<EditText>(R.id.ed_name)
        val tx_view = findViewById<TextView>(R.id.tx_view)
        val radioGroup = findViewById<RadioGroup>(R.id.rg)
        val rb_s = findViewById<RadioButton>(R.id.rb_s)
        val rb_r = findViewById<RadioButton>(R.id.rb_r)
        val rb_p = findViewById<RadioButton>(R.id.rb_p)
        val tx_name = findViewById<TextView>(R.id.tx_name)
        val btn = findViewById<Button>(R.id.btn)
        val tx_winner = findViewById<TextView>(R.id.tx_winner)
        val tx_mytrn = findViewById<TextView>(R.id.tx_myturn)
        val tx_cputurn = findViewById<TextView>(R.id.tx_cputurn)

        //設定按鈕事件
        btn.setOnClickListener(){
            //如果沒輸入名字
            if(ed_name.length()<1)
                tx_view.text = "請玩家輸入名字"
            else{
                //顯示玩家名字
                tx_name.text = "名字\n${ed_name.text}"
                //顯示玩家的出拳
                tx_mytrn.text = "我方出拳\n${if(rb_s.isChecked) "剪刀"
                else if(rb_r.isChecked) "石頭" else "布"}"
                //以亂數來決定電腦出拳
                val cpu = (Math.random()*3).toInt()
                tx_cputurn.text = "電腦出拳\n${if(cpu==0) "剪刀"
                else if(cpu==1) "石頭" else "布"}"

                when {
                    //玩家勝利判斷
                    rb_s.isChecked && cpu == 2 ||
                            rb_r.isChecked && cpu == 0 ||
                            rb_p.isChecked && cpu == 1 -> {
                        tx_winner.text = "勝利者\n${ed_name.text}"
                        tx_view.text = "恭喜玩家勝利"
                    }
                    //電腦勝利判斷
                    rb_s.isChecked && cpu == 1 ||
                            rb_r.isChecked && cpu == 2 ||
                            rb_p.isChecked && cpu == 0 -> {
                        tx_winner.text = "勝利者\n電腦"
                        tx_view.text = "恭喜電腦勝利"
                    }
                    //平局判斷
                    else -> {
                        tx_winner.text = "勝利者\n平手"
                        tx_view.text = "平局，請再比一場"
                    }
                }
            }
        }
    }
}