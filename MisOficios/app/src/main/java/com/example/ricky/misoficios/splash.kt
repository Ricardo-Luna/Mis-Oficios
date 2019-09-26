package com.example.ricky.misoficios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.ImageView

class splash : AppCompatActivity() {
    val DURACION = 1000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val im : ImageView = findViewById(R.id.imageView5)
        im.setOnClickListener {
            val intent = Intent(baseContext, login::class.java)
            startActivity(intent)
        }
        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(1000)

                    val intent = Intent(baseContext, login::class.java)
                    startActivity(intent)

                }catch (e: Exception)
                {
                    e.printStackTrace()
                }
            }

        }
        background.start()
    }

}
