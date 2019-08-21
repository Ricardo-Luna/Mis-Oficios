package com.example.ricky.misoficios.adaptador

import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Fragmentos.MainFragment
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterOficios(var list: ArrayList<Oficios>, var fragmentManager: FragmentManager) :
    RecyclerView.Adapter<AdapterOficios.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Oficios) {
            val asunto: TextView = itemView.findViewById(R.id.txtAsunto)
            val destinatario: TextView = itemView.findViewById(R.id.txtDestinatario)
            val remitente: TextView = itemView.findViewById(R.id.txtRemitente)
            val fecha: TextView = itemView.findViewById(R.id.txtFecha)
            val folio: TextView = itemView.findViewById(R.id.txtFolio)
            val imagenMensaje: ImageView = itemView.findViewById(R.id.imgAlerta)
            var usuario = SharedPreference.getInstance(itemView.context).usuario

            asunto.text = data.asunto
            destinatario.text = data.destinatario
            remitente.text = data.remitente
            fecha.text = data.fecha
            folio.text = data.codigo

            if (data.importancia?.toInt() == 1) {
                imagenMensaje.setImageResource(R.drawable.icons8_info_127px)
            }
            if (data.importancia?.toInt() == 2) {
                imagenMensaje.setImageResource(R.drawable.icons8_high_priority_127px)
            }

        }
    }
}


