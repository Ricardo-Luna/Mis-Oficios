package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ricky.misoficios.Modelos.Grupos
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterGrupos


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
    lateinit var grupos: ArrayList<Grupos>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_grupos_frg,container, false)
        gruposRecyclerView = view.findViewById(R.id.gruposRecycler)

        val llm: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        gruposRecyclerView.layoutManager = llm

        val adapter= AdapterGrupos(buildGrupos())

        gruposRecyclerView.adapter = adapter

        return view



    }



    fun buildGrupos() : ArrayList<Grupos>{
        grupos = ArrayList()
        grupos.add(Grupos("Grupo 1", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 2", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 3", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 4", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 5", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 6", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 7", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        grupos.add(Grupos("Grupo 8", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
        return grupos
    }
}
