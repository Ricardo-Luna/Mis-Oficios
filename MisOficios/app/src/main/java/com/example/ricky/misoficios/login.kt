package com.example.ricky.misoficios

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.Formatter
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.LoginReq
import com.example.ricky.misoficios.Modelos.LoginRes
import com.example.ricky.misoficios.Modelos.Permiso
import com.example.ricky.misoficios.servicios.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*


class login : AppCompatActivity() {
    object GlobalBASE_URL {
        lateinit var BASE_URL: String
    }

    lateinit var btnIniciar: Button
    lateinit var txuser: EditText
    lateinit var txpw: EditText
    lateinit var dialog: AlertDialog
    lateinit var sAlSession: Switch
    private val PERMISSION_REQUEST = 10

    private var permissions =
        arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        deleteDatabase("wot")
        btnIniciar = findViewById(R.id.btnIniciar)
        sAlSession = findViewById(R.id.recordarSesion)
        txuser = findViewById(R.id.txuser)
        txpw = findViewById(R.id.txpw)
        btnIniciar.setOnClickListener { validarCampos() }
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.my_loading, null)
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission(permissions)) {
                    //  Toast.makeText(this, "Permission are already provided", Toast.LENGTH_SHORT)
                    //      .show()
                } else {
                    val mDialogView =
                        LayoutInflater.from(this).inflate(R.layout.dialog_aviso_inicio, null)
                    val mBuilder = android.support.v7.app.AlertDialog.Builder(this)
                        .setView(mDialogView)
                        .setTitle("Permiso")
                    val mAlertDialog = mBuilder.show()
                    val btn = mDialogView.findViewById<Button>(R.id.btn_permiso)
                    btn.setOnClickListener {
                        try {
                            mAlertDialog.dismiss()
                            requestPermissions(permissions, PERMISSION_REQUEST)
                        } catch (e: Exception) {

                        }
                    }
                }
            } else {
            }
        } catch (e: Exception) {
            println("Falló")
        }
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        getIpAddress()
    }

    fun checkPermission(permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    private fun validarCampos() {
        //  d("LOGIN:", "usr: " + txuser.text.toString() + "pw: " + txpw.text.toString())
        if (txuser.text.toString().isEmpty() || txpw.text.toString().isEmpty()) {
            Toast.makeText(this, "Usuario o contraseña en blanco", Toast.LENGTH_SHORT).show()
        } else {
            dialog.show()
            var loginReq = LoginReq(txuser.text.toString(), txpw.text.toString())

            RetrofitClient.instance.getLogin(loginReq)
                .enqueue(object : Callback<LoginRes> {
                    override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) =
                        if (response.isSuccessful) {
                            if (dialog.isShowing())
                                dialog.dismiss()
                            if (validarPermisos(response.body()!!.Permisos!!)) {

                                val loginRes = response.body()!!
                                loginRes.Password = txpw.text.toString()
                                loginRes!!.Recordar = sAlSession.isChecked
                                loginRes.NickName = txuser.text.toString()

                               // println(
                               //     "Nickname:${loginRes.NickName}\n" +
                               //             "PW: ${loginRes.Password}\n" +
                               //             "Recordar: ${loginRes.Recordar}\n" +
                               //             "Nickname: ${loginRes.NickName} \n" +
                               //             "NombreCompleto: ${loginRes.NombreCompleto} \n" +
                               //             "IdUsuario: ${loginRes.IdUsuario}\n" +
                               //             "Carpeta Inicial: ${loginRes.CarpetaInicial} \n" +
                               //             "Permisos: ${loginRes.Permisos} \n" +
                               //             "Carpeta Seleccionada: ${loginRes.carpetaSeleccionada}\n"
//
                               // )

                                SharedPreference.getInstance(applicationContext)
                                    .saveUsuario(loginRes)
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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

                    override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                        if (dialog.isShowing())
                            dialog.dismiss()
                        // Log.e("Response", t.message)
                        Toast.makeText(
                            applicationContext,
                            "Error en la red, vuelve a intentarlo",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    private fun validarPermisos(permisos: List<Permiso>): Boolean {
        val posicion = permisos.indexOfFirst { it.numeroPermiso == 1000 }
        if (posicion > -1) {
            return true
        }
        return false
    }

    private fun getIpAddress() {
        val IP: String
        val manager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        IP = Formatter.formatIpAddress(manager.connectionInfo.ipAddress)

        GlobalBASE_URL.BASE_URL = "http://10.0.0.17/ApiMovilMisOficios/api/"

    }
}


/*
//{Deprecated methods}
//Database block--------------------------------
//  val dbHandler = DBHelper(this@login, null)
//  dbHandler.clearDatabase()
//
//  dbHandler.addID(loginRes.IdUsuario.toString())
//  val cursor = dbHandler.getID()
//  cursor!!.moveToFirst()
//d("INICIO: ", cursor.getString(0).toString())
////////////////////////////////////////////////
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
override fun onStart() {
         super.onStart()

            val intent = Intent(applicationContext, MainActivity::class.java)
           // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
    }
 */

