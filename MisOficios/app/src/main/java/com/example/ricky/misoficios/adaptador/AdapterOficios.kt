package com.example.ricky.misoficios.adaptador

import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R


class AdapterOficios(var list: ArrayList<Oficios>) :
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
        fun bindItems(data: Oficios){
            var aux: String
            val asunto: TextView = itemView.findViewById(R.id.txtAsunto)
            //val destinatario: TextView = itemView.findViewById(R.id.txtDestinatario)
            val remitente: TextView = itemView.findViewById(R.id.txtRemitente)
            val fecha: TextView = itemView.findViewById(R.id.txtFecha)
            val folio: TextView = itemView.findViewById(R.id.txtFolio)
            val imagenMensaje: ImageView = itemView.findViewById(R.id.imgAlerta)
            var usuario = SharedPreference.getInstance(itemView.context).usuario

            asunto.text = data.Titulo
            // destinatario.text = data.
            // remitente.text = data.IdPropietario
            fecha.text = data.FechaEnvio
            folio.text = data.Codigo
            remitente.text = data.PropietarioNombreCompleto
            if (data.Importancia?.toInt() == 1) {
                imagenMensaje.setImageResource(R.drawable.attention)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia baja", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }

                if (data.Importancia?.toInt() == 2) {
                    imagenMensaje.setImageResource(R.drawable.icons8_info_127px)
                    imagenMensaje.setOnClickListener { view ->
                        Snackbar.make(view, "Importancia normal", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show()
                    }
                }
                    if (data.Importancia?.toInt() == 3) {
                        imagenMensaje.setImageResource(R.drawable.icons8_high_priority_127px)
                        imagenMensaje.setOnClickListener { view ->
                            Snackbar.make(view, "Importancia alta", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show()
                        }


                    }





            }

        }
    }


