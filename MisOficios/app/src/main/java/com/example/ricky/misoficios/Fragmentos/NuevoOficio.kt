package com.example.ricky.misoficios.Fragmentos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.ricky.misoficios.R

class NuevoOficio : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_oficio)


        displayFragment(-1)
        val ib = findViewById<Button>(R.id.btn_Siguiente)

        ib.setOnClickListener { it
        displayFragment(it.id)
        }

    }


      fun displayFragment(id: Int)
      {

          val fragment = when (id) {
              R.id.backOficios ->
              {
                  CreateDoc()
              }
              R.id.btn_Siguiente ->
              {
                 CreateDoc2()
              }


              else ->{
                  CreateDoc()
              }
          }


              supportFragmentManager.beginTransaction().replace(
                  R.id.relativeLayoutOficios,
                  fragment
              ).addToBackStack(null).commit()

    }

}
