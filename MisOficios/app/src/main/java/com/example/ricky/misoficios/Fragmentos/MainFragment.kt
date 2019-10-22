package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.DBHelper
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Almacenado.dbOficios
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.Modelos.folder
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterCarpetas
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.MisOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import com.example.ricky.misoficios.servicios.RetrofitClient.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {

    lateinit var oficiosRecycler: RecyclerView
    lateinit var carpetasRecycler: RecyclerView
    lateinit var oficiosList: ArrayList<Oficios>
    lateinit var carpetasList: ArrayList<Carpetas>
    lateinit var txtFecha: TextView
    lateinit var carpetaSeleccionada: String
    var nombreCarpeta: String = "Recibidos"
    lateinit var idrecibidos: String
    val globvar = MisOficios()

    //lateinit var id: String
    //val mydb =  DBHelper(MainActivity(), null)

    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var listener: lista_usuarios.OnFragmentInteractionListener? = null

    //objeto de {servicios/RetrofitClient}
    val api = retrofit.create(MisOficiosAPI::class.java)

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val oficiosDb =  dbOficios(context!!,null)
        var usuario = SharedPreference.getInstance(context!!).usuario
        val view: View = inflater.inflate(R.layout.main_fragment, container, false)
        val carpetasSelected = getCarpetaInicial(usuario.IdUsuario!!)
        (activity as AppCompatActivity).supportActionBar?.title = nombreCarpeta
        oficiosRecycler = view.findViewById(R.id.oficiosRecycler)
        carpetasRecycler = view.findViewById(R.id.recyclerCarpetas)
        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        d("UsuarioOFICIOS", "${usuario.NombreCompleto}")
        getCarpetaInicial(usuario.IdUsuario!!)
        onMostrarCarpetas(usuario.IdUsuario!!)
        mostrarDocumentos(usuario.IdUsuario!!, carpetasSelected)
        val llm = LinearLayoutManager(context)
        val llm2 = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        oficiosRecycler.layoutManager = llm
        carpetasRecycler.layoutManager = llm2
        // --Aquí alterno entre los métodos siguientes
//      --Método que actualiza el layout arrastándolo hacia abajo

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipeRefreshLayout.setRefreshing(true)

            mostrarDocumentos(usuario.IdUsuario!!, carpetasSelected)

            swipeRefreshLayout.setRefreshing(false)
        })
        return view
    }

    fun mostrarDocumentos(id: String, carpeta: String) {
//        var usuario = SharedPreference.getInstance(context!!).idusuario
        api.getDocsCarpetas(
            id, carpeta
        )
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(
                    call: Call<List<Documentos>>,
                    response: Response<List<Documentos>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {

                            val Documentos = response.body()
                            var fin = response.body()?.size
                            val adapter = AdapterOficios(buildOficios(Documentos!!))
                            d("Tamaño de Response: ", fin.toString())
                            oficiosRecycler.adapter = adapter

                        } else {
                            d("Response Oficios:", "recibido vacío")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                    d("onResponse: ", "Response no recibido")
                }
            }
            )
    }



    fun addToDatabase(id: String, carpeta: String) {
//        var usuario = SharedPreference.getInstance(context!!).idusuario
        api.getDocsCarpetas(
            id, carpeta
        )
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(
                    call: Call<List<Documentos>>,
                    response: Response<List<Documentos>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {

                            val Documentos = response.body()
                            var fin = response.body()?.size


                        } else {
                            d("Response Oficios:", "recibido vacío")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                    d("onResponse: ", "Response no recibido")
                }
            }
            )
    }



    fun getIDUser(): String {
        val dbHandler = DBHelper(context!!, null)
        val bd = dbHandler.writableDatabase
        var id = ""
        val idb = bd.rawQuery("Select id from datos", null)
        //val carpetaRecibidos = bd.rawQuery("Select Carpeta_Recibidos from datos", null)
        if (idb.moveToFirst()) {
            id = idb.getString(0).toString()

        } else d("XXXFRAGMENT", "Error")

        bd.close()
        globvar.usuarioId = id
        return id
    }

    fun getCarpetaInicial(id: String): String {
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
        var usuario = SharedPreference.getInstance(context!!).usuario
        var idrecibidos = ""
        api.getCarpetaRecibidos(id)
            .enqueue(object : Callback<List<Carpetas>> {
                override fun onResponse(
                    call: Call<List<Carpetas>>,
                    response: Response<List<Carpetas>>
                ) {
                    if (response.isSuccessful) {
                        val fold = response.body()!!
                        idrecibidos = fold[0].IdCarpeta.toString()
                        mostrarDocumentos(usuario.IdUsuario.toString(), idrecibidos)
                        //d("XYXCARPETARECIBIDOS: ", idrecibidos)

                    } else {
                        d("INGESU", "YA CHINGO ASU MARE COMARE")
                    }
                }

                override fun onFailure(call: Call<List<Carpetas>>, t: Throwable) {
                    d("INGESU", " THIS AINT FUNNY ANYMORE")
                }
            })
        d("XYXCARPETARECIBIDOS: ", idrecibidos)
        return idrecibidos
    }

    fun buildOficios(G: List<Documentos>): ArrayList<Oficios> {
        oficiosList = ArrayList()
        for (item in G) {
            oficiosList.add(
                Oficios(
                    item.IdDocumento,
                    item.Titulo,
                    item.FechaEnvio,
                    item.IdPropietario,
                    item.idDocumentoRemitente,
                    item.IdCarpeta,
                    item.Codigo,
                    item.Importancia,
                    item.estatus,
                    item.PropietarioNombreCompleto
                )
            )
        }
        return oficiosList
    }

    fun onMostrarCarpetas(id: String) {
        RetrofitClient.instance.getCarpetas(id) //id)
            .enqueue(object : Callback<List<folder>> {
                override fun onResponse(
                    call: Call<List<folder>>,
                    response: Response<List<folder>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            val folder = response.body()
                            //d("Response recibido", "onResponse: ${response.body()!![1].Nombre}")
                            val adapter2 =
                                AdapterCarpetas(
                                    oficiosRecycler,
                                    buildCarpetas(folder!!),
                                    context!!,
                                    activity as AppCompatActivity
                                )
                            carpetasRecycler.adapter = adapter2
                        } else {
                            Toast.makeText(
                                context,
                                "Sin carpetas para mostrar",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<folder>>, t: Throwable) {
                    d("USUARIO: onFailure: ", "Fallo en MainFragment")
                }

            })
    }

    // --Función para generar los oficios, que recibe una lista de tipo Documentos, cuya estructura está en Modelos/Oficios
    fun buildCarpetas(G: List<folder>): ArrayList<Carpetas> {
        carpetasList = ArrayList()
        for (item in G) {
            carpetasList.add(
                Carpetas(
                    item.IdCarpeta,
                    item.IdUsuarioPropietario,
                    item.Nombre,
                    item.Recibidos,
                    item.Enviados,
                    item.Borradores,
                    item.IdUsuarioActualizacion,
                    item.FechaActualizacion
                )
            )
        }
        return carpetasList
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}

// --Función para probar el valor del dato recibido en el Response, por lo que no quedará en la versión final
// fun onActualizarLista2() {
//     api.getDocsCarpetas(
//        idUser,
//         "8d1d65b9-64c6-402c-8a00-2bb7ab20a181"
//
//     )
//         .enqueue(object : Callback<List<Documentos>> {
//             override fun onResponse(
//                 call: Call<List<Documentos>>,
//                 response: Response<List<Documentos>>
//             ) {
// //                  d("onResponse", "Response succesful  ${response.body()!![0].Titulo}")
//             }
//
//             override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
//                 d("onResponse", "FAILURE")
//             }
//         })
// }

// --Función que recibe los datos del onResponse y los trata para mostrarlos en el recyclerView,
//   actualmente

// public fun recibirDatos(carpeta: String, nombre: String){
//     carpetaSeleccionada = carpeta
//     nombreCarpeta = nombre
//     (activity as AppCompatActivity).supportActionBar?.title = nombreCarpeta
// }


//var fin = response.body()?.size
//fun onActualizarLista() {
//
//    var usuario = SharedPreference.getInstance(context!!).usuario
//    RetrofitClient.instance.getDocumentos(//usuario.IdUsuario <-Este es el usuario de la aplicación,
//        // que se puede intercambiar por la línea siguiente
//        "ae10550a-cf5c-4912-aed6-3b0adbcde508"    //  <----
//    )
//        .enqueue(object : Callback<List<Documentos2>>{
//            override fun onFailure(call: Call<List<Documentos2>>, t: Throwable) {
//                Log.e("onFailure", t.message)
//            }
//
//            override fun onResponse(call: Call<List<Documentos2>>, response: Response<List<Documentos2>>) {
//                if (response.isSuccessful) {
//                    if (!response.body().isNullOrEmpty()) {
//
//                        val Documentos2 = response.body()
//                        var fin = response.body()?.size
//                        //  Toast.makeText(
//                        //      context,
//                        //      "Response Sucessful, " + fin + " elements",
//                        //      Toast.LENGTH_SHORT
//                        //  ).show()
//                        val adapter = AdapterOficios(buildOficios(Documentos2!!))
//                        oficiosRecycler.adapter = adapter
//                    }
//                }
//            }
//
//        })
//
//}
//


/*
 // Aquí toma los oficios de la lista
    val adapter = AdapterOficios(buildOficios())
    oficiosRecycler.adapter = adapter
*/
//Comienza onCreate
//sheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(btm_carpetas)
//sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
//    override fun onStateChanged(bottomSheet: View, newState: Int) {
//        when (newState) {
//            BottomSheetBehavior.STATE_HIDDEN -> {
//            }
//            BottomSheetBehavior.STATE_EXPANDED ->
//                txtcarpetas.text = "Cerrar"
//            BottomSheetBehavior.STATE_COLLAPSED ->
//                txtcarpetas.text = "Carpetas"
//            BottomSheetBehavior.STATE_DRAGGING -> {
//            }
//            BottomSheetBehavior.STATE_SETTLING -> {
//            }
//        }
//    }
//    override fun onSlide(bottomSheet: View, slideOffset: Float) {
//    }
//})
//    txtcarpetas.setOnClickListener(View.OnClickListener {
//        expandCloseSheet()
//    })
// Termina onCreate

//private fun expandCloseSheet() {
//    if (sheetBehavior!!.state != BottomSheetBehavior.STATE_EXPANDED) {
//        sheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
//        txtcarpetas.text = "Cerrar"
//    } else {
//        sheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
//        txtcarpetas.text = "Carpetas"
//    }
//}


