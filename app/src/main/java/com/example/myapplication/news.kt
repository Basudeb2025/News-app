package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class news : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    private var check = false
    lateinit var imgview: ImageView
   lateinit var dialog : Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        if(supportActionBar != null){
            supportActionBar!!.hide()
        }
        //The dialog for show the loading bar
        val dai = Dialog(this)
        dai.setContentView(R.layout.loaddia)
        //The id of the recyclerview
        recyclerView = findViewById(R.id.recyclerid)
        imgview = findViewById(R.id.choose)
        //Build the retrofit third party library
        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitdata = retrofit.create(myinterface::class.java)
        val country = "in"
        val headname = findViewById<TextView>(R.id.head)
        //For the default value
        calltheApi(country,retrofitdata,dai)
        //The list for the chose country
        imgview.setOnClickListener { view ->
            val popmenu = PopupMenu(this, view)
            popmenu.inflate(R.menu.listofcoun)
            popmenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_india -> {
                        val c = "in"
                        calltheApi(c,retrofitdata,dai)
                        headname.text = "INDIA-NEWS"
                        true
                    }
                    R.id.item_america -> {
                        val u = "us"
                        calltheApi(u,retrofitdata, dai)
                        headname.text = "AMERICA-NEWS"
                        true
                    }
                    R.id.item_china -> {
                        val ch = "ch"
                        calltheApi(ch,retrofitdata,dai)
                        headname.text = "CHINA-NEWS"
                        true
                    }
                    R.id.item_japan -> {
                        val jp = "jp"
                        calltheApi(jp,retrofitdata,dai)
                        headname.text = "JAPAN-NEWS"
                        true
                    }
                    R.id.item_austre->{
                        val au = "au"
                        calltheApi(au,retrofitdata,dai)
                        headname.text = "AUSTRALIA-NEWS"
                        true
                    }
                    else -> false
                }
            }
            popmenu.show()
        }
    }
    private fun calltheApi(country: String, retrofitdata: myinterface?,dai: Dialog) {
        //The dialog showing if the check is false, the check will be false only on the first, so when we will enter on the screen
        // the dialog would be show and check would be true, and the check never would be false anymore, because it is a global variable
        if(!check){
            dai.show()
            check = true
        }
        //The Api key
        val apikey = "62afae4c83b34ff7b3b614b9dff07f6d"
        // call the api , by the interface and the country name, api key
        val call = retrofitdata?.getTopHeadlines(country,apikey)
        call?.enqueue(object : Callback<allData?> {
            override fun onResponse(call: Call<allData?>?, response: Response<allData?>?) {
                val response = response?.body()
                // when the data would be show the dialog will dismis
                dai.dismiss()
                val articl = response?.articles?: emptyList()
                val adter = myadapter(this@news,articl)
                //Connect the adapter with recycler view
                recyclerView.adapter = adter
                recyclerView.layoutManager = LinearLayoutManager(this@news)
            }
            override fun onFailure(call: Call<allData?>?, t: Throwable?) {
                dai.dismiss()
                Toast.makeText(this@news,"Sorry! unable to load",Toast.LENGTH_SHORT).show()
            }
        })
    }
}