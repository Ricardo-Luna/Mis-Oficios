package com.example.ricky.misoficios

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.format.Formatter
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.*
import com.example.ricky.misoficios.servicios.RetrofitClient
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class splash : AppCompatActivity() {
    val DURACION = 3000
    lateinit var btnIniciar: Button
    lateinit var txuser: EditText
    lateinit var txpw: EditText
    lateinit var dialog: AlertDialog

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val im1: ImageView = findViewById(R.id.imageView4)
        val text: TextView = findViewById(R.id.oficiosText)
        val im: ImageView = findViewById(R.id.imageView5)





        im1.setOnClickListener {
            text.visibility = View.GONE
            AnimationUtils.loadAnimation(this, R.anim.animacion_escalacion)
                .also { hyperspaceJumpAnimation ->
                    //  findViewById<TextView>(R.id.oficiosText).startAnimation(hyperspaceJumpAnimation)
                    findViewById<ImageView>(R.id.imageView4).startAnimation(hyperspaceJumpAnimation)
                }
        }

        im.setOnClickListener {
            iniciar()
        }
        val background = object : Thread() {

            override fun run() {
                try {
                    sleep(1000)

                    iniciar()

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        background.start()
    }

    //////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    private fun iniciar() {
        try {
            val usuario = SharedPreference.getInstance(applicationContext).usuario
            val com = SharedPreference.getInstance(applicationContext).isLoggedIn.toString()
            val pw = PasswordTransformationMethod.getInstance().toString()
            println("${usuario.IdUsuario.toString()}, ${usuario.Password.toString()}, $pw")
            Log.d("Estado de sesión: ", "$com")
            validarCampos(usuario.IdUsuario.toString(), "123")

        } catch (e: Exception) {
            val intent = Intent(baseContext, login::class.java)
            startActivity(intent)
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    private fun validarCampos(nickname: String, password: String) {
        dialog.show()
        var loginReq = LoginReq(nickname, password)

        RetrofitClient.instance.getLogin(loginReq)
            .enqueue(object : Callback<LoginRes> {
                override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                    if (dialog.isShowing())
                        dialog.dismiss()
                    Log.e("Response", t.message)
                    Toast.makeText(
                        applicationContext,
                        "Has perdido la conexión a internet, reconéctate e inténtalo de nuevo",
                        Toast.LENGTH_LONG
                    ).show()
                }

                ////////////////////////////////////////////////////////////////////////////////////////////////
                override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) =
                    if (response.isSuccessful) {
                        if (dialog.isShowing())
                            dialog.dismiss()
                        if (validarPermisos(response.body()!!.Permisos!!)) {
                            val loginRes = response.body()!!
                            val intent = Intent(applicationContext, MainActivity::class.java)
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
            })
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

        login.GlobalBASE_URL.BASE_URL = "http://10.0.0.17/ApiMovilMisOficios/api/"

    }
}
// loginRes.NickName = txuser.text.toString()
// SharedPreference.getInstance(applicationContext)
//     .saveUsuario(
//         loginRes,
//         loginRes.IdUsuario.toString()
//     )     //cursor.getString(0))
// intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK