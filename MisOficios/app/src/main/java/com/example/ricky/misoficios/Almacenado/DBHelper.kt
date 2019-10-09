package com.example.ricky.misoficios.Almacenado

import android.app.PendingIntent.getActivity
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.lang.Exception
import java.security.AccessController.getContext
import java.util.prefs.PreferencesFactory

class DBHelper(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    factory, DATABASE_VERSION
) {
    val cont = context

    override fun onCreate(db: SQLiteDatabase) {

        clearDatabase(TABLE_NAME, db)
        makeDatabase(TABLE_NAME, db)

    }

    fun clearDatabase(TABLE_NAME: String, db: SQLiteDatabase) {
        val clearDBQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(clearDBQuery)
    }

    fun makeDatabase(TABLE_NAME: String, db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + " ( "
                + COLUMN_ID + " TEXT, " +
                COLUM_CARPETASELECTED + "  TEXT, " +
                COLUM_CARPETARECIBIDOS + " TEXT)"
                )
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun addID(id: String) {

        val values = ContentValues()
        values.put(COLUMN_ID, id)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun addCarpetaRecibidos(id: String) {
        val values = ContentValues()
        values.put(COLUM_CARPETARECIBIDOS, id)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getID(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("Select * from $TABLE_NAME", null)
    }

    fun getCarpetaRecibidos(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("Select * from $TABLE_NAME", null)
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "wot"
        val TABLE_NAME = "datos"
        val COLUMN_ID = "id"
        val COLUM_CARPETASELECTED = "Carpeta_Seleccionada"
        val COLUM_CARPETARECIBIDOS = "Carpeta_Recibidos"
    }
}

//val cursor = dbHandler.getCarpetaRecibidos()
//cursor!!.moveToFirst()