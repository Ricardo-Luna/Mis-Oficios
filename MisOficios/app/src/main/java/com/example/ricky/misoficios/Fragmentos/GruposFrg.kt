package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Grupos
import com.example.ricky.misoficios.Modelos.Gruposrv
import com.example.ricky.misoficios.Modelos.Integrantes
import com.example.ricky.misoficios.Modelos.usuariosGruposR
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterGrupos
import com.example.ricky.misoficios.adaptador.AdapterUsuariosGrupos
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient

class GruposFrg : Fragment() {
    lateinit var gruposRecyclerView : RecyclerView
    lateinit var grupos: ArrayList<Gruposrv>
    var grupoid: String = ""
    lateinit var rv : RecyclerView
    lateinit var usuariosList: ArrayList<usuariosGruposR>
    public fun recibirDatosGrupo(grupo: String){
        grupoid = grupo

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_grupos_frg,container, false)
        gruposRecyclerView = view.findViewById(R.id.gruposRecycler)

        val fr = lista_usuarios()
        mostrarUsuarios()
        val llm2: LinearLayoutManager = LinearLayoutManager(context)
        val llm: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        gruposRecyclerView.layoutManager = llm

        //val tx1:  TextView = view.findViewById(R.id.textView10)
        //val tx2:  TextView = view.findViewById(R.id.actualizacion)
        //tx1.visibility =  View.GONE
        //tx2.visibility =  View.GONE
        // rv.layoutManager = llm2
        mostrarGrupos()
        return view

    }

    fun mostrarGrupos()
    {
        var usuario = SharedPreference.getInstance(context!!).usuario
        RetrofitClient.instance.getGrupos(//usuario.IdUsuario <-Este es el usuario de la aplicación,
            // que se puede intercambiar por la línea siguiente
            "3F66D2E6-57CB-48CD-8632-21EC91F80421"

        ).enqueue(object: Callback<List<Grupos>>{
            override fun onResponse(call: Call<List<Grupos>>, response: Response<List<Grupos>>) {
                if(response.isSuccessful)
                {
                    Log.d("Response", "onResponse: ${response.body()!![0].IdGrupo}")
                    val Grupos = response.body()
                    val adapter = AdapterGrupos(fragmentManager,buildGrupos((Grupos!!)))
                    gruposRecyclerView.adapter = adapter
                }
            }
            override fun onFailure(call: Call<List<Grupos>>, t: Throwable) {
                Log.d("Response Fallido:", " Falló ela respuesta en el response")
            }
        })
    }

    fun buildGrupos(G: List<Grupos>):ArrayList<Gruposrv>{
        grupos = ArrayList()
        for (item in G)
        {
            grupos.add(
                Gruposrv(
                    item.IdGrupo,
                    item.Nombre,
                    item.IdUsuarioActualizacion,
                    item.FechaActualizacion,
                    item.PropietarioNombreCompleto
                )
            )
        }
        return grupos
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
                            //  Log.d("Response Usuarios","onResponse: ${grupoid}")
                            val Integrantes = response.body()
                            val adapter = AdapterUsuariosGrupos(buildIntegrantes(Integrantes!!))
                            rv.adapter = adapter

                        } else {
                            //  Log.d("Response UsuariosGrupo:", "recibido vacío, "+ { grupoid })
                        }
                    }
                }

                override fun onFailure(call: Call<List<Integrantes>>, t: Throwable) {
                    //   Log.d("Response Usuarios:", "Error en response " + { grupoid })
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










//val adapter= AdapterGrupos(buildGrupos())
//
//gruposRecyclerView.adapter = adapter





   // fun buildGrupos() : ArrayList<Grupos>{
   //     grupos = ArrayList()
   //     grupos.add(Grupos("Grupo 1", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 2", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 3", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 4", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 5", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 6", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 7", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     grupos.add(Grupos("Grupo 8", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
   //     return grupos
   // }

