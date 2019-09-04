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
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Grupos
import com.example.ricky.misoficios.Modelos.Gruposrv
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterGrupos
import com.example.ricky.misoficios.servicios.RetrofitClient


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GruposFrg.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GruposFrg.newInstance] factory method to
 * create an instance of this fragment.
 *
 */




class GruposFrg : Fragment() {
    lateinit var gruposRecyclerView : RecyclerView
    lateinit var grupos: ArrayList<Gruposrv>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_grupos_frg,container, false)
        gruposRecyclerView = view.findViewById(R.id.gruposRecycler)

        val llm: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        gruposRecyclerView.layoutManager = llm

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
                    Log.d("Response", "onResponse: ${response.body()!![0].Nombre}")
                    val Grupos = response.body()
                    val adapter = AdapterGrupos(buildGrupos((Grupos!!)))
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
                    item.idUsuarioPropietario,
                    item.Nombre,
                    item.IdUsuarioActualizacion,
                    item.FechaActualizacion
                )
            )
        }
        return grupos
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

