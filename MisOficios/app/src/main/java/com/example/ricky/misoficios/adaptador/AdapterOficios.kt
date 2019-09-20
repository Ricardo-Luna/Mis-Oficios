package com.example.ricky.misoficios.adaptador


import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Fragmentos.mostrarDocumento
import com.example.ricky.misoficios.MainActivity
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R
import java.lang.Exception
import java.text.SimpleDateFormat
import kotlin.coroutines.coroutineContext


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
        fun bindItems(data: Oficios) {
            val asunto: TextView = itemView.findViewById(R.id.txtAsunto)
            val remitente: TextView = itemView.findViewById(R.id.txtRemitente)
            val fecha: TextView = itemView.findViewById(R.id.txtFecha)
            val folio: TextView = itemView.findViewById(R.id.txtFolio)
            val imagenMensaje: ImageView = itemView.findViewById(R.id.imgAlerta)
            val iv: ImageView = itemView.findViewById(R.id.iv)
            var usuario = SharedPreference.getInstance(itemView.context).usuario
            asunto.text = data.Titulo


            if (data.FechaEnvio != null) {
                try {
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("HH:mm, dd/MM/yyyy")
                    val formattedDate = formatter.format(parser.parse(data.FechaEnvio))
                    fecha.text = formattedDate
                } catch (e: Exception) {
                    fecha.text = data.FechaEnvio
                }
            }


            folio.text = data.Codigo
            remitente.text = data.PropietarioNombreCompleto
            if (data.estatus?.toInt() == 1) {
                iv.setImageResource(R.drawable.nw)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Nuevo Documento", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 2) {
                iv.setImageResource(R.drawable.ic_menu_send)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Documento enviado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 3) {
                iv.setImageResource(R.drawable.rvs)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Recibido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 4) {
                iv.setImageResource(R.drawable.ic_rd)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Leído", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 5) {
                iv.setImageResource(R.drawable.deleted)
                iv.setOnClickListener { view ->
                    Snackbar.make(
                        view,
                        "Jajja we esto ni siquiera debería salir",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }
            }

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


