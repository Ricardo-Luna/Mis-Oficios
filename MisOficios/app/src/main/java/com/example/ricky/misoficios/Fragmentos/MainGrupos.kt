package com.example.ricky.misoficios.Fragmentos

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ricky.misoficios.Modelos.Grupos
import com.example.ricky.misoficios.Modelos.Oficios

import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterGrupos
import com.example.ricky.misoficios.adaptador.AdapterOficios
import kotlinx.android.synthetic.main.fragment_grupos_frg.*
import kotlinx.android.synthetic.main.fragment_grupos_frg.view.*

class MainGrupos : Fragment() {

    lateinit var gruposRecyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_grupos_frg,container, false)
        gruposRecyclerView = view.findViewById(R.id.gruposRecycler)

        val llm: LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        gruposRecycler.layoutManager = llm

        //val adapter= AdapterGrupos(buildGrupos())
        //gruposRecycler.adapter = adapter

        return view



    }




}