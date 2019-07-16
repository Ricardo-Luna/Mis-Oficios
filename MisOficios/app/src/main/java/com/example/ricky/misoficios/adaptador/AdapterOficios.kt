package com.example.ricky.misoficios.adaptador

import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R

 class AdapterOficios(var list: ArrayList <Oficios>): RecyclerView.Adapter<AdapterOficios.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

     override fun getItemCount(): Int {
         return list.size
     }

     override fun onBindViewHolder(holder: AdapterOficios.ViewHolder, position: Int) {
         holder.bindItems(list[position])
     }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItems(data:Oficios){
            val asunto : TextView = itemView.findViewById(R.id.txtAsunto)
            val destinatario : TextView = itemView.findViewById(R.id.txtDestinatario)
            val remitente : TextView = itemView.findViewById(R.id.txtRemitente)
            val fecha : TextView = itemView.findViewById(R.id.txtFecha)
            val folio : TextView = itemView.findViewById(R.id.txtFolio)
            val imagenMensaje : ImageView = itemView.findViewById(R.id.imgAlerta)

           // val codigo : TextView =
            asunto.text = data.asunto
            destinatario.text = data.destinatario
            remitente.text = data.remitente
            fecha.text = data.fecha
            folio.text = data.codigo

                if (data.importancia.toInt()==1){
                    imagenMensaje.setImageResource(R.drawable.icons8_info_127px)
                }
                if (data.importancia.toInt()==2){
                    imagenMensaje.setImageResource(R.drawable.icons8_high_priority_127px)
                }


            imagenMensaje.setOnClickListener {
                Snackbar.make(itemView, "Aquí se muestra la importancia del mensaje", Snackbar.LENGTH_SHORT).show()
            }


        }
    }
}