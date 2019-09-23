package com.example.ricky.misoficios.adaptador

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.Empleados
import com.example.ricky.misoficios.Modelos.EmpleadosGrupo
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.itm_usuarios.view.*

class AdapterListaEmpleados(var list: ArrayList<Empleados>) :
    RecyclerView.Adapter<AdapterListaEmpleados.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_usuarios, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterListaEmpleados.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var list: ArrayList<EmpleadosGrupo>
        fun bindItems(data: Empleados) {
            val nombre: TextView = itemView.findViewById(R.id.txtEmpleado)
            val puesto: TextView = itemView.findViewById(R.id.txtArea)
            val agregar: Button = itemView.findViewById(R.id.btnAgregar)
            nombre.text = data.nombre
            puesto.text = data.puesto
            val nomaux = nombre.text
            val pueaux = puesto.text

            nombre.setOnClickListener {
                Snackbar.make(itemView, "Empleado: " + nombre.text, Snackbar.LENGTH_SHORT).show()
            }
            agregar.setOnClickListener {
                list.add(EmpleadosGrupo(nomaux as String, pueaux as String))
            }
        }
    }

}