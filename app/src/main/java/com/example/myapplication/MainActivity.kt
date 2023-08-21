package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //
        if(supportActionBar != null){
            supportActionBar!!.hide()
        }
        val prog = findViewById<ProgressBar>(R.id.progressBar)
        prog.visibility = View.VISIBLE
        Handler().postDelayed({
            startActivity(Intent(this,news::class.java))
            prog.visibility = View.GONE
            finish()
        },3600
        )

    }
}