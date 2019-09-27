package com.example.ricky.misoficios

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.media.Image
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.oficios
import kotlinx.android.synthetic.main.activity_splash.*

class splash : AppCompatActivity() {
    val DURACION = 1000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val im1 :ImageView = findViewById(R.id.imageView4)
        val text : TextView=findViewById(R.id.oficiosText)
        val im : ImageView = findViewById(R.id.imageView5)

        im1.setOnClickListener {
        text.visibility = View.GONE
            AnimationUtils.loadAnimation(this, R.anim.animacion_escalacion).also {
                    hyperspaceJumpAnimation ->
              //  findViewById<TextView>(R.id.oficiosText).startAnimation(hyperspaceJumpAnimation)
                findViewById<ImageView>(R.id.imageView4).startAnimation(hyperspaceJumpAnimation)
            }


        }


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
