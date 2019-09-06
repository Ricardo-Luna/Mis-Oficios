package com.example.ricky.misoficios.adaptador

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.activity_mostrar_documento.*
import java.io.File

class mostrarDocumento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_documento)
        wb.loadUrl("file:///android_asset/ss.html")

    }
    fun nuevoDoc()
    {
        File("data.html").forEachLine { println(it) }
    }
}
