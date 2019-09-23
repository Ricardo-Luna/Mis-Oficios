package com.example.ricky.misoficios.Fragmentos

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.webkit.WebSettings
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.activity_mostrar_documento.*
import java.io.File

class mostrarDocumento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_documento)
        //wb.loadUrl("https://www.amazon.com.mx/")
        wb.loadUrl("file:///android_asset/ss.html")
        wb.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR)
        wb.getSettings().setBuiltInZoomControls(true)

        val fab: FloatingActionButton = findViewById(R.id.fabFirmar)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Presione de nuevo para firmar documento", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            wb.loadUrl("https://www.amazon.com.mx/")
        }
        val faback: FloatingActionButton = findViewById(R.id.floatingActionButton)
        faback.setOnClickListener { view ->
            Snackbar.make(view, "Presione de nuevo para ir atrás", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
          //  val intent = Intent(baseContext, MainActivity::class.java)
          //  startActivity(intent)
            wb.loadUrl("file:///android_asset/ss.html")
        }

    }

    fun nuevoDoc() {
        File("/android_asset/ss.html").forEachLine { println(it) }
    }
}
