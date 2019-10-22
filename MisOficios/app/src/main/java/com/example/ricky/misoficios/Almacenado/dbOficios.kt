package com.example.ricky.misoficios.Almacenado

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbOficios(
    context: Context,
    factory: SQLiteDatabase.CursorFactory?
): SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    factory, DATABASE_VERSION
)
{
    override fun onCreate(db: SQLiteDatabase) {
        makeDatabase(TABLE_NAME,db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val clearDBQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(clearDBQuery)
    }

    fun createDatabase(){
        val db = this.readableDatabase
        makeDatabase(TABLE_NAME,db)
    }

    fun makeDatabase(TABLE_NAME: String, db: SQLiteDatabase)
    {
        val createTable = (
                "Create table  $TABLE_NAME (" +
                        "$_idDocumento          TEXT,"+
                        "$_Titulo               TEXT,"+
                        "$_fechaenvio           TEXT,"+
                        "$_idpropietario        TEXT,"+
                        "$_iddocumentoremitente TEXT,"+
                        "$_idcarpeta            TEXT,"+
                        "$_codigo               TEXT,"+
                        "$_importancia          TEXT,"+
                        "$_estatus              TEXT,"+
                        "$_propietario          TEXT,"+
                        "$_remitente            TEXT,"+
                        "$_fechaLectura          TEXT"+
                        ")"
                )
        db.execSQL(createTable)
    }

    fun getOficiosDb(): Cursor
    {
        val db = this.readableDatabase
        val bd = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        return bd
    }

    fun insertOficiosDb(
        idDocumento            : String,
        Titulo                 : String?,
        fechaenvio             : String?,
        idpropietario          : String?,
        iddocumentoremitente   : String?,
        idcarpeta              : String?,
        codigo                 : String?,
        importancia            : String?,
        estatus                : String?,
        propietario            : String?,
        fechaLectura           : String?
    )

    {
        val values = ContentValues()
        values.put(_idDocumento           ,idDocumento         )
        values.put(_Titulo               ,idDocumento          )
        values.put(_fechaenvio           ,Titulo               )
        values.put(_idpropietario        ,fechaenvio           )
        values.put(_iddocumentoremitente ,idpropietario        )
        values.put(_idcarpeta            ,iddocumentoremitente )
        values.put(_codigo               ,idcarpeta            )
        values.put(_importancia          ,codigo               )
        values.put(_estatus              ,importancia          )
        values.put(_propietario          ,estatus              )
        values.put(_remitente            ,propietario          )
        values.put(_fechaLectura         ,fechaLectura         )
        val db = this.writableDatabase
        db.insert(TABLE_NAME,null,values)
        db.close()
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "misOficios"
        val TABLE_NAME            = "Documentos"
        val _idDocumento          = "id"
        val _Titulo               = "titulo"
        val _fechaenvio           = "fechaenvio"
        val _idpropietario        = "idpropietario"
        val _iddocumentoremitente = "iddocumentoremitente"
        val _idcarpeta            = "idcarpeta"
        val _codigo               = "codigo"
        val _importancia          = "importancia"
        val _estatus              = "estatus"
        val _propietario          = "propietario"
        val _remitente            = "Remitente"
        val _fechaLectura         = "fechaLectura"

    }
}