package com.example.ricky.misoficios.servicios

import android.app.Application

import android.content.Context
import android.util.Log
import android.util.Log.d
import com.example.ricky.misoficios.Almacenado.DBHelper
import com.example.ricky.misoficios.Almacenado.SharedPreference

class MisOficios : Application() {
    lateinit var usuarioId: String

    companion object {
        lateinit var carpeta: String
        var cadena: String = ""
        lateinit var carpetaSelect: String
    }

    fun getCadena(): String {
        return cadena
    }

    fun setCadena(cad: String) {
        lateinit var cadena: String
        cadena = cad
    }

    fun setCarpetaSeleccionada(carpeta: String) {
        carpetaSelect = carpeta
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

    //fun setValores(carpeta: String, carpetaSelected: String, id: String) {
    //    carpeta = carpeta
    //    carpetaSelect = carpetaSelected
    //    usuarioId = id
    //}

    fun getIDUsuario(context: Context): String {
        val dbHandler = DBHelper(context, null)
        val cursor = dbHandler.getID()
        cursor!!.moveToFirst()
        usuarioId = cursor.getString(cursor.getColumnIndex("id")).toString()
        return usuarioId
    }

}