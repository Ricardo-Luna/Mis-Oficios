package com.example.ricky.misoficios.adaptador


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.usuariosGruposR
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.itm_usuarios.view.*

class AdapterUsuariosGrupos(var list: ArrayList<usuariosGruposR>) :
    RecyclerView.Adapter<AdapterUsuariosGrupos.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_usuarios, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: usuariosGruposR) {
            val nombre: TextView = itemView.findViewById(R.id.txtEmpleado)
            nombre.text = data.UsuarioNombreCompleto
        }
    }
}