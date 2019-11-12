package com.example.ricky.misoficios.Fragmentos

import android.content.Context
import android.content.DialogInterface
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
import kotlin.collections.ArrayList


//wb.loadUrl("https://www.amazon.com.mx/")
class mostrarDocumento : AppCompatActivity() {

    //Variables
    lateinit var vistosList: ArrayList<Remitente>
    lateinit var rv: RecyclerView
    private val PERMISSION_REQUEST = 10
    lateinit var dialog: AlertDialog
    private var permissions =
        arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )


    ///On Create////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar_documento)
        wb.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR)
        wb.getSettings().setBuiltInZoomControls(true)


        try {
            wb.loadUrl("file:////sdcard/ss2.html")
        } catch (e: Exception) {
        }
        val fab: FloatingActionButton = findViewById(R.id.fabFirmar)

        fab.setOnClickListener { view ->
            createDoc()
            val bundle = Bundle()
            // wb.loadUrl("https://www.amazon.com.mx/")
            ShowDialog()
        }
        val faback: FloatingActionButton = findViewById(R.id.floatingActionButton)
        //  nuevoDoc()
        faback.setOnClickListener {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkPermission(this, permissions)) {
                        Toast.makeText(this, "Permission are already provided", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        ShowDialogPermiso()
                        requestPermissions(permissions, PERMISSION_REQUEST)
                    }
                } else {
                    Toast.makeText(this, "Permission are already provided", Toast.LENGTH_SHORT)
                        .show()
                }
                //view ->
                //Snackbar.make(view, "Documento creado", Snackbar.LENGTH_LONG)
                //    .setAction("Action", null).show()
                //  val intent = Intent(baseContext, MainActivity::class.java)
                //  startActivity(intent)
            } catch (e: Exception) {
                println("Falló")
            }
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    //Permission Block////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Todo lo que tiene que ver con solicitar permisos para poder sobreescribir el documento mostrado en el webview
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            var allSuccess = true
            for (i in permissions.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                    allSuccess = false
                    var requestAgain =
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && shouldShowRequestPermissionRationale(
                            permissions[i]
                        )
                    if (requestAgain) {
                        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this,
                            "Go to settings and enable the permission",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            if (allSuccess)
                Toast.makeText(this, "Permissions Granted", Toast.LENGTH_SHORT).show()
        }
    }

    fun checkPermission(context: Context, permissionArray: Array<String>): Boolean {
        var allSuccess = true
        for (i in permissionArray.indices) {
            if (checkCallingOrSelfPermission(permissionArray[i]) == PackageManager.PERMISSION_DENIED)
                allSuccess = false
        }
        return allSuccess
    }

    fun createDoc() {
        try {

            //makeRequest()
            val fileName = "/sdcard/ss2.html"
            var file = File(fileName)
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
            wb.loadUrl("file:////sdcard/ss2.html")
        } catch (e: Exception) {
            println("Fallo al crear archivo: $e")
        }
    }


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    ///Funciones que muestran el dialog de firmar/////////////////////////////////////////////////////////
    private fun ShowDialogPermiso() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_aviso_inicio, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Firmar")
        val mAlertDialog = mBuilder.show()
        val btn = mDialogView.findViewById<Button>(R.id.Firmar)
        btn.setOnClickListener{
          //  mDialogView.
        }

    }

    private fun ShowDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_confirm, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("Firmar")
            //.setCancelable(false)
        val mAlertDialog = mBuilder.show()
        // dialog.setContentView(R.layout.dialog_confirm)
        val usuario = mDialogView.findViewById<TextView>(R.id.username)
        val password = mDialogView.findViewById<TextView>(R.id.password)
        val firmar = mDialogView.findViewById<Button>(R.id.Firmar)
        firmar.setOnClickListener {
            println(mDialogView.display.toString())
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
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