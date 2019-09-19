package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.example.ricky.misoficios.Modelos.buildEmpleado
import com.example.ricky.misoficios.Modelos.buildEmpleadosGrupo
import com.example.ricky.misoficios.Modelos.*
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterListaEmpleados
import com.example.ricky.misoficios.adaptador.AdapterListaEmpleadosAdd

class listaEmpleados : Fragment() {

    lateinit var groupsRecyclerView: RecyclerView
    var spinner: Spinner? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view2: View = inflater.inflate(R.layout.itm_grupo_nw, container, false)
        groupsRecyclerView = view2.findViewById(R.id.listUser)
        val llmm: LinearLayoutManager = LinearLayoutManager(context)
        llmm.orientation = LinearLayout.VERTICAL
        groupsRecyclerView.layoutManager = llmm
        val adapter2 = AdapterListaEmpleadosAdd(buildEmpleadosGrupo())
        groupsRecyclerView.adapter = adapter2
        return view2
    }
}