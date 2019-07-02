package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ricky.misoficios.Modelos.buildEmpleado
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterListaEmpleados


class NwGrupo : Fragment() {
    lateinit var empleadosRecycler : RecyclerView

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.itm_grupo_nw, container, false)
        empleadosRecycler= view.findViewById(R.id.listUser)

        val llm : LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        empleadosRecycler.layoutManager = llm

        val adapter = AdapterListaEmpleados(buildEmpleado())
        empleadosRecycler.adapter = adapter

        return view
    }

}