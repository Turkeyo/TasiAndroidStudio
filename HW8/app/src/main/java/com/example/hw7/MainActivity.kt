package com.example.hw7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //變數綁定XML元件
        val spinner = findViewById<Spinner>(R.id.spinner)
        val gridView = findViewById<GridView>(R.id.gridView)
        val listView = findViewById<ListView>(R.id.listView)
        val count = ArrayList<String>() //儲存購買數量資訊
        val item = ArrayList<Item>() //儲存水果資訊
        val priceRange = IntRange(10,100) //建立價格範圍
        val array =
            resources.obtainTypedArray(R.array.image_list) //從R類別讀取圖檔
        for(i in 0 until array.length()){
            val photo = array.getResourceId(i,0) //設定水果Id
            val name = "水果${i+1}" //水果名稱
            val price = priceRange.random() //使用亂數定義價格
            count.add("${i+1}個") //新增可購買數量資訊
            item.add(Item(photo,name,price)) //新增水果資訊
        }
        array.recycle() //釋放圖片資源
        //建立 ArrayAdapter 物件，並傳入字串與 simple_list_item_1.xml
        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,count)
        //設定橫向顯示列數 = 3
        gridView.numColumns = 3
        //建立 MyAdapter 物件，並串入adapter_vertical 為畫面
        gridView.adapter = MyAdapter(this,item,R.layout.adapter_vertical)
        //建立 MyAdapter 物件，並串入adapter_horizontal 為畫面
        listView.adapter = MyAdapter(this,item,R.layout.adapter_horizontal)
    }
}

//設計新的類別定義水果資料
data class Item(
    val photo: Int, //圖片
    val name: String, //名稱
    val price: Int //價格
)