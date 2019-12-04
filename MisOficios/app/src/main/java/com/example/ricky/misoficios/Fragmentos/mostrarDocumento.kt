package com.example.ricky.misoficios.Fragmentos

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.webkit.WebSettings
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.activity_mostrar_documento.*
import java.io.File
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.ricky.misoficios.Modelos.Remitente
import android.Manifest
import android.content.Intent
import com.example.ricky.misoficios.MainActivity
import kotlin.collections.ArrayList
import android.support.v4.app.SupportActivity
import android.support.v4.app.SupportActivity.ExtraData
import android.support.v4.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View

//import android.R


class mostrarDocumento : AppCompatActivity() {

    //Variables
    //lateinit var vistosList: ArrayList<Remitente>
    //lateinit var rv: RecyclerView
    //private val PERMISSION_REQUEST = 10
    //
    //private var permissions =
    //    arrayOf(
    //        Manifest.permission.WRITE_EXTERNAL_STORAGE,
    //        Manifest.permission.READ_EXTERNAL_STORAGE
    //    )

    ///On Create////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_documento)
        wb.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR)
        val tipo: String = intent.getStringExtra("Tipo")
        wb.getSettings().setBuiltInZoomControls(true)
        if(tipo!="1") fabFirmar.hide()

        println("Tipo: $tipo")
        try {
            wb.loadUrl("file:////sdcard/ss2.html")
        } catch (e: Exception) {
        }
        val fab = findViewById<FloatingActionButton>(R.id.fabFirmar)

        fab.setOnClickListener { view ->
            ShowDialog()
        }

        val faback: FloatingActionButton = findViewById(R.id.floatingActionButton)
        faback.setOnClickListener {
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun ShowDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirm, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Firmar")
            .setCancelable(false)
        val mAlertDialog = mBuilder.show()
        // dialog.setContentView(R.layout.dialog_confirm)
        val usuario = mDialogView.findViewById<TextView>(R.id.username)
        val password = mDialogView.findViewById<TextView>(R.id.password)
        val firmar = mDialogView.findViewById<Button>(R.id.Firmar)
        val cancelar = mDialogView.findViewById<Button>(R.id.cancelarFirma)
        firmar.setOnClickListener {
            mAlertDialog.dismiss()
        }
        cancelar.setOnClickListener {
            mAlertDialog.dismiss()
        }

    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
