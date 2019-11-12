package com.example.ricky.misoficios.Almacenado

import android.content.Context
import com.example.ricky.misoficios.Modelos.LoginRes
import com.google.gson.Gson

class SharedPreference private constructor(private val mCtx: Context) {
    private val SHARED_PREF_NAME = "spOficios"
    var gruposid = ""
    val gson = Gson()
    val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    val usuario: LoginRes
        get() {
            val serializedUser = sharedPreferences.getString("Usuario", null)
            return gson.fromJson(serializedUser, LoginRes::class.java)
        }

    val carpeta: LoginRes
    get(){
        val serializedUser = sharedPreferences.getString("carpetaSeleccionada",null)
        return  gson.fromJson(serializedUser,LoginRes::class.java)
    }
    val isLoggedIn: Boolean
        get() {
            return sharedPreferences.getInt("Status", -1) != -1
        }

    fun saveUsuario(usuario: LoginRes,id: String) {
        val serializedUser = gson.toJson(usuario)
        sharedPreferences.edit().putString("Usuario", serializedUser).apply()
        sharedPreferences.edit().putString("Id",id).apply()
    }
    fun limpiar() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
    companion object {
        private var mInstance: SharedPreference? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPreference {
            if (mInstance == null) {
                mInstance =
                    SharedPreference(mCtx)
            }
            return mInstance as SharedPreference
        }
    }
}