package com.example.ricky.misoficios.Fragmentos

import android.content.Context
import android.app.AlertDialog
import android.os.Bundle
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
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.Modelos.valor

import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import com.example.ricky.misoficios.servicios.RetrofitClient.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ricky.misoficios.MainActivity as MainActivity


class MainFragment : Fragment() {
    lateinit var dialog: AlertDialog
    lateinit var oficiosRecycler: RecyclerView
    lateinit var oficiosList: ArrayList<Oficios>


    //objeto de servicios/RetrofitClient
    val api = retrofit.create(MisOficiosAPI::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.main_fragment, container, false)
        oficiosRecycler = view.findViewById(R.id.oficiosRecycler)
        var swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        val llm: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        oficiosRecycler.layoutManager = llm

        // --Aquí alterno entre los métodos siguientes
        // onActualizarLista2()
        onActualizarLista()

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipeRefreshLayout.setRefreshing(true)
            onActualizarLista2()
            swipeRefreshLayout.setRefreshing(false)
        })
        return view
    }

    // --Función para probar el valor del dato recibido en el Response, por lo que no quedará en la versión final
    fun onActualizarLista2() {
        api.getDocumentos("ae10550a-cf5c-4912-aed6-3b0adbcde508")
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
                    d("Response recibido","onResponse: ${response.body()!![0].asunto}")
                }

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    d("Response No Recibido","onFailure")
                }


            }

            )
    }

    // --Función que recibe los datos del onResponse y los trata para mostrarlos en el recyclerView,
    //   actualmente


    fun onActualizarLista() {

        var usuario = SharedPreference.getInstance(context!!).usuario
        RetrofitClient.instance.getDocumentos(//usuario.IdUsuario <-Este es el usuario de la aplicación,
                                                            // que se puede intercambiar por la línea siguiente
            "ae10550a-cf5c-4912-aed6-3b0adbcde508"    //  <----
        )
            .enqueue(object : Callback<List<Documentos>> {

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                }

                override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {

                            val Documentos = response.body()
                            var fin = response.body()?.size
                            Toast.makeText(
                                context,
                                "Response Sucessful, " + fin+" elements",
                                Toast.LENGTH_SHORT
                            ).show()
                            val adapter = AdapterOficios(buildOficios(Documentos!!), fragmentManager!!)
                            oficiosRecycler.adapter = adapter
                        }
                    }
                }
            })
    }

    // --Función para generar los oficios, que recibe una lista de tipo Documentos, cuya estructura está en Modelos/Oficios

    fun buildOficios(G: List<Documentos>): ArrayList<Oficios> {
        oficiosList = ArrayList()
        for (item in G) {
            oficiosList.add(
                Oficios(
                    item.asunto,
                    item.remitente,
                    item.destinatario,
                    item.fecha,
                    item.folio,
                    item.codigo,
                    item.importancia
                )
            )
        }
        return oficiosList
    }


/*

 // Aquí toma los oficios de la lista

    val adapter = AdapterOficios(buildOficios())

    oficiosRecycler.adapter = adapter
*/


}
