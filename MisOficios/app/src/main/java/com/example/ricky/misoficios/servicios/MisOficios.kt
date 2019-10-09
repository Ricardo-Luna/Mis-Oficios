package com.example.ricky.misoficios.servicios

import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import android.util.Log.d
import com.example.ricky.misoficios.Almacenado.DBHelper
import com.example.ricky.misoficios.Almacenado.SharedPreference

class MisOficios : Application() {

    var carpeta = ""
    lateinit var carpetaSelect: String
    lateinit var usuarioId: String

    fun setValores(carpeta: String, carpetaSelected: String, id: String) {
        this.carpeta = carpeta
        this.carpetaSelect = carpetaSelected
        this.usuarioId = id
    }

    fun getIDUsuario(context: Context): String {
        val dbHandler = DBHelper(context, null)
        val cursor = dbHandler.getID()
        cursor!!.moveToFirst()
        usuarioId = cursor.getString(cursor.getColumnIndex("id")).toString()
        return usuarioId
    }

    fun getCarpetaInicial(context: Context): String {
        val dbHandler = DBHelper(context, null)
        val cursor = dbHandler.getCarpetaRecibidos()
        cursor!!.moveToLast()
        try {
            val str =
                cursor.getString(2).toString()
            carpeta = str

        } catch (e: Exception) {
            d("XXXEXCEPTION : ", e.toString())
        }
        return carpeta
    }

    fun setCarpetaSeleccionada(carpeta: String) {
        this.carpetaSelect = carpeta
    }

    fun getCarpetaSeleccionada(): String {
        return carpetaSelect
    }

    fun getId(): String {
        return usuarioId
    }

    fun getCarpetaActual(): String {
        return carpeta
    }

}

