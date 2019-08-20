package com.example.ricky.misoficios.Fragmentos

import android.content.Context
import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios

import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.RetrofitClient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ricky.misoficios.MainActivity as MainActivity


class MainFragment : Fragment() {
    lateinit var dialog: AlertDialog
    lateinit var oficiosRecycler: RecyclerView
    lateinit var oficiosList: ArrayList<Oficios>
    lateinit var prov: ArrayList<Documentos>
    var IdUsuario: String = ""
    var Opcion: Int = 0


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

        onActualizarLista()

        //  buildOficios(prov)
        //  val adapter = AdapterOficios(buildOficios(prov),fragmentManager)
        //  oficiosRecycler.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipeRefreshLayout.setRefreshing(true)
            onActualizarLista()
            swipeRefreshLayout.setRefreshing(false)
        })
        return view
    }

    fun onActualizarLista() {
        //   dialog.show()
        var usuario = SharedPreference.getInstance(context!!).usuario
        RetrofitClient.instance.getDocumentos(//usuario.IdUsuario
            "ae10550a-cf5c-4912-aed6-3b0adbcde508" , "0", "19")
            .enqueue(object : Callback<List<Documentos>> {

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    Log.e("onFailure", t.message)

                    //       if (dialog.isShowing())
                    //           dialog.dismiss()

                }

                override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {

                            var fin = response.body()?.size
                            Toast.makeText(
                                context,
                                "Response Sucessful " + fin,
                                Toast.LENGTH_SHORT
                            ).show()
                            val adapter = AdapterOficios(buildOficios(response.body()!!), fragmentManager!!)
                            oficiosRecycler.adapter = adapter
                        }

                        else {
                            var fin = response.body()?.size
                            Toast.makeText(
                                context,
                                "Response Sucessful " + fin,
                                Toast.LENGTH_SHORT
                            ).show()
                            val adapter = AdapterOficios(buildOficios(response.body()!!), fragmentManager!!)
                            oficiosRecycler.adapter = adapter

                        }
                    }
                }

            })
    }

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
