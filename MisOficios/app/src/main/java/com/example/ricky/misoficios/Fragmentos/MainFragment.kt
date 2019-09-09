package com.example.ricky.misoficios.Fragmentos


import android.app.AlertDialog
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
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
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.*

import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import com.example.ricky.misoficios.servicios.RetrofitClient.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ricky.misoficios.MainActivity as MainActivity

import android.support.design.widget.BottomSheetBehavior
import com.example.ricky.misoficios.adaptador.AdapterCarpetas


class MainFragment : Fragment() {
    lateinit var dialog: AlertDialog
    lateinit var oficiosRecycler: RecyclerView
    lateinit var carpetasRecycler: RecyclerView
    lateinit var oficiosList: ArrayList<Oficios>
    lateinit var carpetasList: ArrayList<Carpetas>
    lateinit var txtFecha: TextView
    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>
    //objeto de {servicios/RetrofitClient}
    val api = retrofit.create(MisOficiosAPI::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.main_fragment, container, false)

        oficiosRecycler = view.findViewById(R.id.oficiosRecycler)
        carpetasRecycler = view.findViewById(R.id.recyclerCarpetas)

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        onMostrarCarpetas()
        mostrarDocumentos()
        onActualizarLista2()
        val llm: LinearLayoutManager = LinearLayoutManager(context)
        val llm2: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        oficiosRecycler.layoutManager = llm
        carpetasRecycler.layoutManager = llm2
        // --Aquí alterno entre los métodos siguientes
//      --Método que actualiza el layout arrastándolo hacia abajo
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipeRefreshLayout.setRefreshing(true)
            mostrarDocumentos()
            swipeRefreshLayout.setRefreshing(false)
        })
        return view
    }

    // --Función para probar el valor del dato recibido en el Response, por lo que no quedará en la versión final
    fun onActualizarLista2() {
        api.getDocsCarpetas("ae10550a-cf5c-4912-aed6-3b0adbcde508", "8995969b-6837-46d2-bd91-485c4d3ee8c2")
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
//                   d("onResponse", "Response succesful  ${response.body()!![0].Titulo}")
                }

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    d("onResponse", "FAILURE")
                }
            })
    }

    // --Función que recibe los datos del onResponse y los trata para mostrarlos en el recyclerView,
    //   actualmente
    public fun mostrarDocumentos() {
        api.getDocsCarpetas(
            "ae10550a-cf5c-4912-aed6-3b0adbcde508",    //  <----
            "8995969b-6837-46d2-bd91-485c4d3ee8c2"
        )
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            d("Response recibido", "onResponse: ${response.body()!![0].Titulo}")
                            val Documentos = response.body()
                            var fin = response.body()?.size
                            val adapter = AdapterOficios(buildOficios(Documentos!!))
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

    fun onMostrarCarpetas() {
        var usuario = SharedPreference.getInstance(context!!).usuario
        RetrofitClient.instance.getCarpetas("b3be6e2f-7e79-474c-9985-fab45ed8956a")
            .enqueue(object : Callback<List<folder>> {
                override fun onResponse(call: Call<List<folder>>, response: Response<List<folder>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            val folder = response.body()
                            d("Response recibido", "onResponse: ${response.body()!![1].Nombre}")
                            val adapter2 = AdapterCarpetas(buildCarpetas(folder!!))
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
}


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


