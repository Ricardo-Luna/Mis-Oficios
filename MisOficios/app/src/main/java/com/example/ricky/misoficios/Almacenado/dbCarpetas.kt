package com.example.ricky.misoficios.Almacenado

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbCarpetascontext(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
): SQLiteOpenHelper(
context,
DATABASE_NAME,
factory, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
      //  makeDatabase(dbOficios.TABLE_NAME,db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val clearDBQuery = "DROP TABLE IF EXISTS ${dbOficios.TABLE_NAME}"
        db.execSQL(clearDBQuery)
    }



    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "misOficios"
        val TABLE_NAME            = "Documentos"
        val _idCarpeta            = "idCarpeta"
        val _nombreCarpeta        = "nombreCarpeta"

    }
}