package com.example.ricky.misoficios.adaptador


import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R
import java.lang.Exception
import java.text.SimpleDateFormat


class AdapterOficios(var list: ArrayList<Oficios>) :
    RecyclerView.Adapter<AdapterOficios.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
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
            val asunto: TextView = itemView.findViewById(R.id.Asunto)
            val remitente: TextView = itemView.findViewById(R.id.Remitentes)
            val fecha: TextView = itemView.findViewById(R.id.Fecha)
            val folio: TextView = itemView.findViewById(R.id.Folio)
            val imagenMensaje: ImageView = itemView.findViewById(R.id.imageImportancia)
            val iv: ImageView = itemView.findViewById(R.id.imageRecibido)
            val ivv: ImageView = itemView.findViewById(R.id.imageLeido)
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
                iv.setImageResource(R.drawable.nuevo)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Nuevo Documento", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 2) {
                iv.setImageResource(R.drawable.enviado)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Documento enviado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 3) {
                iv.setImageResource(R.drawable.recibido)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Recibido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 4) {
                ivv.setImageResource(R.drawable.leido)
                ivv.setOnClickListener { view ->
                    Snackbar.make(view, "Leído", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 5) {
                ivv.setImageResource(R.drawable.deleted)
                ivv.setOnClickListener { view ->
                    Snackbar.make(
                        view,
                        "Jajja we esto ni siquiera debería salir",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }
            }

            if (data.Importancia?.toInt() == 1) {
                imagenMensaje.setImageResource(R.drawable.importancia_baja)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia baja", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.Importancia?.toInt() == 2) {
                imagenMensaje.setImageResource(R.drawable.importancia_normal)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia normal", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.Importancia?.toInt() == 3) {
                imagenMensaje.setImageResource(R.drawable.imporancia_alta)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia alta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
        }
    }
}


