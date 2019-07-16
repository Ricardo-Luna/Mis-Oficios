package com.example.ricky.misoficios

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.format.Formatter
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.ricky.misoficios.Modelos.Permiso
import com.example.ricky.misoficios.servicios.SharedPreference

class login : AppCompatActivity() {
 //  object GlobalBASE_URL {
 //      lateinit var BASE_URL: String
 //   }
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

    private fun validarPermisos ( permisos: List<Permiso> ): Boolean  {
        val posicion = permisos.indexOfFirst { it.Numero == 130  }
        if ( posicion > -1) {
            return true
        }
        return false
    }
    override fun onStart() {
        super.onStart()
        if(SharedPreference.getInstance(this).isLoggedIn){
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
  //  private fun getIpAddress() {
  //      val IP: String
  //      val manager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
  //      IP = Formatter.formatIpAddress(manager.connectionInfo.ipAddress)
//
  //          GlobalBASE_URL.BASE_URL= "/http://localhost:50577/api/Documentos"
//
  //  }
}
