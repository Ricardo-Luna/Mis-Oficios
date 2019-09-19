package com.example.ricky.misoficios.Fragmentos

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ricky.misoficios.Modelos.Integrantes
import com.example.ricky.misoficios.Modelos.usuariosGruposR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterUsuariosGrupos
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient


var grupoid: String = ""

class lista_usuarios : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    lateinit var integrantesList: ArrayList<Integrantes>
    lateinit var usuariosList: ArrayList<usuariosGruposR>
    lateinit var recyclerIntegrantes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_lista_usuarios, container, false)
        mostrarUsuarios()
        recyclerIntegrantes = view.findViewById(R.id.integrantesRV)
        val llm: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        recyclerIntegrantes.layoutManager = llm
        return view
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }


    fun mostrarUsuarios() {
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
        api.getUsuariosGrupos(grupoid)
            .enqueue(object : Callback<List<Integrantes>> {
                override fun onResponse(
                    call: Call<List<Integrantes>>,
                    response: Response<List<Integrantes>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            Log.d(
                                "Response recibido",
                                "onResponse: ${response.body()!![0].UsuarioNombreCompleto}"
                            )

                            val Integrantes = response.body()
                            val adapter = AdapterUsuariosGrupos(buildIntegrantes(Integrantes!!))
                            recyclerIntegrantes.adapter = adapter

                        } else {
                            Log.d("Response Oficios:", "recibido vacío")
                        }

                    }
                }

                override fun onFailure(call: Call<List<Integrantes>>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })

    }

    fun buildIntegrantes(G: List<Integrantes>): ArrayList<usuariosGruposR> {
        usuariosList = ArrayList()
        for (item in G) {
            usuariosList.add(
                usuariosGruposR(
                    item.UsuarioNombreCompleto
                )
            )
        }
        return usuariosList
    }


}















































































