package com.example.ricky.misoficios.adaptador

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.Remitente
import com.example.ricky.misoficios.R
import org.w3c.dom.Text

class adapterVistos(var list: ArrayList<Remitente>) :
    RecyclerView.Adapter<adapterVistos.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_personas_vistos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Remitente) {
            val nombre: TextView = itemView.findViewById(R.id.textViewDesinatario)
            val fecha: TextView = itemView.findViewById(R.id.textViewFechaLeido)
            val img : ImageView = itemView.findViewById(R.id.imageLeido)
            nombre.text = data.UsuarioNombreCompleto
            if (!data.FechaLectura.equals(""))
            {
                fecha.text = data.FechaLectura
            }
            else {
                img.setImageResource(R.drawable.no_leido)
                fecha.text = "NO LEÍDO"
            }

        }
    }
}