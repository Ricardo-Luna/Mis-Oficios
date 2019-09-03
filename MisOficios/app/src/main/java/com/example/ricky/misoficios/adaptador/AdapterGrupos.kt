package com.example.ricky.misoficios.adaptador

import android.media.Image
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import  android.widget.ImageButton
import com.example.ricky.misoficios.Modelos.Grupos
import com.example.ricky.misoficios.R
import kotlinx.android.synthetic.main.itm_grupos.view.*

class AdapterGrupos(var list: ArrayList<Grupos>) : RecyclerView.Adapter<AdapterGrupos.ViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_grupos, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdapterGrupos.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

class ViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bindItems(data:Grupos){
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val destinatario: TextView = itemView.findViewById(R.id.destinatarios)
        val propietarios: TextView = itemView.findViewById(R.id.Propietario)
        val fecha: TextView = itemView.findViewById(R.id.actualización)
        val editar: ImageButton = itemView.findViewById(R.id.editar)
        val eliminar: ImageButton = itemView.findViewById(R.id.eliminar)

        nombre.text = data.nombre
        destinatario.text = data.destinatarios
        propietarios.text = data.propietario
        fecha.text = data.fecha

        editar.setOnClickListener {
            Snackbar.make(itemView, "Dará la posibilidad de editar el grupo", Snackbar.LENGTH_SHORT).show()
        }

        eliminar.setOnClickListener {
            Snackbar.make(itemView, "Dará la posibilidad de eliminar el grupo", Snackbar.LENGTH_SHORT).show()
        }

    }



}


}