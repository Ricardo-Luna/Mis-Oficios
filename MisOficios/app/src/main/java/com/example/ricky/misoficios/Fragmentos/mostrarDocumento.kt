package com.example.ricky.misoficios.Fragmentos

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.webkit.WebSettings
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.activity_mostrar_documento.*
import java.io.File
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog
import java.io.IOException
import java.io.OutputStreamWriter
import android.widget.Toast
















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
            val bundle = Bundle()
            onCreateDialog(R.layout.dialog_confirm)

            val dl = AlertDialog.Builder(this)
            dl.setTitle("Chinga tu madre Ed Maverick")
                dl.setMessage("Seguro que quieres mandar a chingar a su madre a Ed Maverick?")
            dl.setPositiveButton("Que chingue a su puta madre",
                DialogInterface.OnClickListener { dialogo1, id -> aceptar() })
            dl.setNegativeButton("Que chingue a su puta madre",
                DialogInterface.OnClickListener { dialogo1, id -> aceptar() })
            dl.show()

            //
            //     Snackbar.make(view, "Presione de nuevo para firmar documento", Snackbar.LENGTH_LONG)
            //         .setAction("Action", null).show()
            //     wb.loadUrl("https://www.amazon.com.mx/")
            // }
            val faback: FloatingActionButton = findViewById(R.id.floatingActionButton)
            nuevoDoc()
            faback.setOnClickListener { view ->
                Snackbar.make(view, "Documento creado", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
                //  val intent = Intent(baseContext, MainActivity::class.java)
                //  startActivity(intent)
                wb.loadUrl("file:///android_asset/ss.html")

            }
        }
    }

    fun nuevoDoc() {
        try {
            val archivo = OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE))
            archivo.write("Salinas mató a colosio we obvio")
            archivo.flush()
            archivo.close()
        } catch (e: IOException) {
        }
    }
    fun aceptar() {
        val t = Toast.makeText(this, "Gracias por mandar a chingar a su madre a Ed Maverick", Toast.LENGTH_SHORT)
        t.show()
    }
    fun cancelar() {
        finish()
    }
    override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
        return mostrarDocumento().let {
            val builder = AlertDialog.Builder(it)
            val inflater = mostrarDocumento().layoutInflater;

            builder.setView(inflater.inflate(R.layout.dialog_confirm, null))
                // Add action buttons
                .setPositiveButton(R.string.signin,
                    DialogInterface.OnClickListener { dialog, id ->
                        // sign in the user ...
                        wb.loadUrl("file:///android_asset/ss.html")
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // getDialog().cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
// override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//     return mostrarDocumento().let {
//         val builder = AlertDialog.Builder(it)
//         // Get the layout inflater
//         val inflater = mostrarDocumento().layoutInflater;
//
//         // Inflate and set the layout for the dialog
//         // Pass null as the parent view because its going in the dialog layout
//         builder.setView(inflater.inflate(R.layout.dialog_confirm, null))
//             // Add action buttons
//             .setPositiveButton(R.string.signin,
//                 DialogInterface.OnClickListener { dialog, id ->
//                     // sign in the user ...
//                     wb.loadUrl("file:///android_asset/ss.html")
//                 })
//             .setNegativeButton(R.string.cancel,
//                 DialogInterface.OnClickListener { dialog, id ->
//                     // getDialog().cancel()
//                 })
//         builder.create()
//     } ?: throw IllegalStateException("Activity cannot be null")
//
// }


