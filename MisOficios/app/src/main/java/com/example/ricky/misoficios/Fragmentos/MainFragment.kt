package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ricky.misoficios.Modelos.buildOficios
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterOficios


class MainFragment : Fragment() {

    lateinit var oficiosRecycler : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.main_fragment, container, false)
        oficiosRecycler = view.findViewById(R.id.oficiosRecycler)

        val llm : LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        oficiosRecycler.layoutManager = llm

        val adapter = AdapterOficios(buildOficios())

        oficiosRecycler.adapter = adapter

        return view
    }







}
