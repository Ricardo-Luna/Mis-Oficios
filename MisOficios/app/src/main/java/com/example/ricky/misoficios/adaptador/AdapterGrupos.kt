package com.example.ricky.misoficios.adaptador

import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ricky.misoficios.Modelos.Gruposrv
import com.example.ricky.misoficios.Modelos.nickname
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AdapterGrupos(var list: ArrayList<Gruposrv>) :
    RecyclerView.Adapter<AdapterGrupos.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_groups, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Gruposrv) {
            val nombre: TextView = itemView.findViewById(R.id.nombre)
            //val usuarioActualizacion: TextView = itemView.findViewById(R.id.usuarioActualizacion)
            val propietario: TextView = itemView.findViewById(R.id.propietario)
            val actualizacion: TextView = itemView.findViewById(R.id.actualizacion)

            propietario.text = data.PropietarioNombreCompleto
            nombre.text = data.Nombre
            //  usuarioActualizacion.text = data.IdUsuarioActualizacion
            //propietario.text = data.idUsuarioPropietario
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



// val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
// api.getNickName(data.IdUsuarioActualizacion!!)
//     .enqueue(object : Callback<nickname> {
//         override fun onResponse(
//             call: retrofit2.Call<nickname>,
//             response: Response<nickname>
//         ) {
//             d("Response recibido", "onResponse: ${response.body()}")
//             usuarioActualizacion.text = response.body().toString()
//         }
//
//         override fun onFailure(call: retrofit2.Call<nickname>, t: Throwable) {
//             d("Grupos:", "Falló el response")
//         }
//     })


