package com.example.ricky.misoficios.Fragmentos

import android.app.Activity
import android.app.Dialog
import android.content.Context
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
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.Window
import android.widget.Button
import android.widget.TextView
import java.io.IOException
import java.io.OutputStreamWriter
import android.widget.Toast
import com.example.ricky.misoficios.Modelos.Remitente
import com.example.ricky.misoficios.Modelos.Remitentes
import com.example.ricky.misoficios.adaptador.adapterVistos
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import kotlinx.android.synthetic.main.dialog_confirm.view.*
import kotlinx.android.synthetic.main.dialog_views.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

//wb.loadUrl("https://www.amazon.com.mx/")
class mostrarDocumento : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_documento)

        createDoc()
        try {
            wb.loadUrl("file:///android_asset/data.html")
        } catch(e : Exception){}
        wb.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR)
        wb.getSettings().setBuiltInZoomControls(true)


        val fab: FloatingActionButton = findViewById(R.id.fabFirmar)
        fab.setOnClickListener { view ->
            val bundle = Bundle()
            wb.loadUrl("file:///android_asset/data.html")
            //ShowDialog()
        }


        val faback: FloatingActionButton = findViewById(R.id.floatingActionButton)
        //  nuevoDoc()
        faback.setOnClickListener { view ->
            Snackbar.make(view, "Documento creado", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            //  val intent = Intent(baseContext, MainActivity::class.java)
            //  startActivity(intent)
            //  wb.loadUrl("file:///android_asset/ss.html")


        }

    }

    fun createDoc() {
        val fileName = "data.html"
        var file = File("file:///android_asset/",fileName)
        val isNewFileCreated: Boolean = file.createNewFile()

        if (isNewFileCreated) {
            println("$fileName is created successfully.")
        } else {
            println("$fileName already exists.")
        }

        // try creating a file that already exists
        val isFileCreated: Boolean = file.createNewFile()

        if (isFileCreated) {
            println("$fileName is created successfully.")
        } else {
            println("$fileName already exists.")
        }
        file.writeText(
            "<HTML>\n" +
                    "<HEAD>\n" +
                    "<TITLE>ejemplo hola mundo</TITLE>\n" +
                    "</HEAD>\n" +
                    "<BODY>\n" +
                    "<P>Hola Mundo</P>\n" +
                    "</BODY>\n" +
                    "</HTML>"
        )

    }

    lateinit var vistosList: ArrayList<Remitente>
    lateinit var rv: RecyclerView

    private fun ShowDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_views, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        //.setTitle("Firmar")
        val mAlertDialog = mBuilder.show()
        rv = mDialogView.rvVistos
        mostrarRemitentes("4ee47204-fd96-4d10-8e3d-bc4baa2af5cc")
        mDialogView.buttonOkViews.setOnClickListener()
        {
            mAlertDialog.dismiss()
        }


    }

    var x = 0
    fun mostrarRemitentes(id: String) {
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)

        api.getRemitentes(id)
            .enqueue(object : Callback<List<Remitentes>> {
                override fun onResponse(
                    call: Call<List<Remitentes>>,
                    response: Response<List<Remitentes>>
                ) {
                    val datos = response.body()
                    val adapter = adapterVistos(buildVistos(datos!!))
                    rv.adapter = adapter
                    var us = response.body()!![x].UsuarioNombreCompleto
                    var fecha = response.body()!![x].FechaLectura
                    // x++
                    Log.d("Usuario ", "$us")
                    Log.d("Fecha ", "$fecha")
                }

                override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
                    Log.d("Mostrar documento", "Hubo un error")
                }
            })
    }

    fun buildVistos(G: List<Remitentes>): ArrayList<Remitente> {
        vistosList = ArrayList()
        for (item in G) {
            vistosList.add(
                Remitente(
                    item.UsuarioNombreCompleto,
                    item.FechaLectura
                )
            )
        }
        return vistosList
    }
}

//private fun showDialogDEP(title: String) {
//    val dialog = Dialog(dialogConfirm(baseContext).context)
//    //val dialog : Dialog = AlertDialog.Builder(baseContext)
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//    dialog.setCancelable(false)
//    dialog.setContentView(R.layout.dialog_confirm)
//    val usuario = dialog.findViewById<TextView>(R.id.username)
//    val password = dialog.findViewById<TextView>(R.id.password)
//    val firmar = dialog.findViewById<Button>(R.id.Firmar)
//    firmar.setOnClickListener {
//        dialog.dismiss()
//    }
//    dialog.show()
//}


//  fun nuevoDoc() {
//      try {
//          val archivo = OutputStreamWriter(openFileOutput("notas.txt", Activity.MODE_PRIVATE))
//          archivo.write("Salinas mató a colosio we obvio")
//          archivo.flush()
//          archivo.close()
//      } catch (e: IOException) {
//      }
//  }

// override fun onCreateDialog(id: Int, args: Bundle?): Dialog? {
//     return mostrarDocumento().let {
//         val builder = AlertDialog.Builder(baseContext)
//         val inflater = dialogConfirm(baseContext).layoutInflater
//
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
// }
//  onCreateDialog(R.layout.dialog_confirm)
//onCreateDialog(R.layout.dialog_confirm, Bundle())
//  val dl = AlertDialog.Builder(this)

//  val usuario = mDialogView.findViewById<TextView>(R.id.username).toString()
//  val password = mDialogView.findViewById<TextView>(R.id.password).toString()
//  val firmar = mDialogView.findViewById<Button>(R.id.Firmar).toString()