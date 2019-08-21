package com.example.ricky.misoficios

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.text.format.Formatter
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.LoginReq
import com.example.ricky.misoficios.Modelos.LoginRes
import com.example.ricky.misoficios.Modelos.Permiso
import com.example.ricky.misoficios.servicios.RetrofitClient

import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class login : AppCompatActivity() {
    object GlobalBASE_URL {
        lateinit var BASE_URL: String
    }

    lateinit var btnIniciar: Button
    lateinit var txuser: EditText
    lateinit var txpw: EditText
    lateinit var dialog: AlertDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnIniciar = findViewById(R.id.btnIniciar)
        txuser = findViewById(R.id.txuser)
        txpw = findViewById(R.id.txpw)

        btnIniciar.setOnClickListener { validarCampos() }

        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.my_loading, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        getIpAddress()


    }

    private fun validarCampos() {

        if (txuser.text.toString().isEmpty() || txpw.text.toString().isEmpty()) {
            Toast.makeText(this, "Usuario o contraseña en blanco", Toast.LENGTH_SHORT).show()
        } else {
            dialog.show()
            var loginReq = LoginReq(txuser.text.toString(), txpw.text.toString())

            RetrofitClient.instance.getLogin(loginReq)
                .enqueue(object : Callback<LoginRes> {
                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        if (dialog.isShowing())
                            dialog.dismiss()
                        Log.e("Response", t.message)
                        Toast.makeText(
                            applicationContext,
                            "[Login] Error: Indica el errror al areas de TI",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) =
                        if (response.isSuccessful) {
                            if (dialog.isShowing())
                                dialog.dismiss()
                            if (validarPermisos(response.body()!!.Permisos)) {
                                Toast.makeText(
                                    applicationContext,
                                    "La tarea falló con éxito",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val loginRes = response.body()!!
                                loginRes.NickName = txuser.text.toString()
                                Log.e("Usuario", loginRes.toString())
                                SharedPreference.getInstance(applicationContext).saveUsuario(loginRes)


                                val intent = Intent(applicationContext, MainActivity::class.java)
                                // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    "El usuario no tiene permiso para usar esta aplicación",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            if (dialog.isShowing())
                                dialog.dismiss()
                            Toast.makeText(
                                applicationContext,
                                "El nombre de usuario o la contraseña son incorrectos",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                }
                )

        }
    }

    private fun validarPermisos(permisos: List<Permiso>): Boolean {
        val posicion = permisos.indexOfFirst { it.numeroPermiso == 1000}
        if (posicion > -1) {
            return true
        }
        return false
    }

   //override fun onStart() {
   //      super.onStart()
//
   //         val intent = Intent(applicationContext, MainActivity::class.java)
   //        // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
   //         startActivity(intent)
//
   // }

    private fun getIpAddress() {
        val IP: String
        val manager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        IP = Formatter.formatIpAddress(manager.connectionInfo.ipAddress)

        GlobalBASE_URL.BASE_URL = "http://10.0.0.17/ApiMovilMisOficios/api/"

    }
}


/*


//{Deprecated methods}

btnIniciar.setOnClickListener{
     val user = txuser.text.toString().trim()
     val pw = txpw.text.toString().trim()
     if(user.isEmpty() || pw.toString().isEmpty())
     {Toast.makeText(this,"Usuario o contraseña en blanco", Toast.LENGTH_SHORT).show()}

 }

private fun validarIn() {
    if (txuser.text.toString().isEmpty() || txpw.text.toString().isEmpty()) {
        Toast.makeText(
            this,
            "Datos inválidos, vuelva a intentar o acuda a TI por un cambio de contraseña",
            Toast.LENGTH_LONG
        ).show()
    } else {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

 */

