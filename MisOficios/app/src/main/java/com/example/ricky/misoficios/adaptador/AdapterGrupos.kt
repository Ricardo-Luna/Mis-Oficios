package com.example.ricky.misoficios.adaptador

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ricky.misoficios.Fragmentos.lista_usuarios
import com.example.ricky.misoficios.Modelos.Gruposrv
import com.example.ricky.misoficios.R
import java.lang.Exception
import java.text.SimpleDateFormat
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import kotlin.coroutines.coroutineContext

class AdapterGrupos(var fragmentManager: FragmentManager?, var list: ArrayList<Gruposrv>) :
    RecyclerView.Adapter<AdapterGrupos.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_groups, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun changeFragment() {
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Gruposrv) {
            val nombre: TextView = itemView.findViewById(R.id.nombre)
            //val usuarioActualizacion: TextView = itemView.findViewById(R.id.usuarioActualizacion)
            val propietario: TextView = itemView.findViewById(R.id.propietario)
            val actualizacion: TextView = itemView.findViewById(R.id.actualizacion)
            val cl: ConstraintLayout = itemView.findViewById(R.id.itvw)

            propietario.text = data.PropietarioNombreCompleto
            nombre.text = data.Nombre
            nombre.setOnClickListener {
                val nuevoFragmento = lista_usuarios()
                nuevoFragmento.recibirDatos(data.IdGrupo)
              //  Log.d("Response UsuariosGrupo:", "recibido vacío, "+ { data.IdGrupo })
                print(data.IdGrupo)
                val transaction = fragmentManager!!.beginTransaction()
                transaction.replace(R.id.linearLayoutContentMain, nuevoFragmento)
                transaction.addToBackStack(null)
                transaction.commit()
            }
            if (data.FechaActualizacion != null) {
                try {
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("HH:mm, dd/MM/yyyy")
                    val formattedDate = formatter.format(parser.parse(data.FechaActualizacion))
                    actualizacion.text = formattedDate
                } catch (e: Exception) {
                    actualizacion.text = data.FechaActualizacion
                }
            }
        }


    }
}




