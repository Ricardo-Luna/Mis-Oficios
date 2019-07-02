package com.example.ricky.misoficios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class login : AppCompatActivity() {

    lateinit var btnIniciar : Button
    lateinit var txuser : EditText
    lateinit var txpw : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnIniciar = findViewById(R.id.btnIniciar)
        txuser = findViewById(R.id.txuser)
        txpw = findViewById(R.id.txpw)

        btnIniciar.setOnClickListener { validarIn() }


    }

    private fun validarIn(){
        if (txuser.text.toString().isEmpty() || txpw.text.toString().isEmpty()){
            Toast.makeText(this, "Datos inválidos, vuelva a intentar o acuda a TI por un cambio de contraseña", Toast.LENGTH_LONG).show()
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
