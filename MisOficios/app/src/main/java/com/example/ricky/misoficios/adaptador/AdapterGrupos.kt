package com.example.ricky.misoficios.adaptador

import android.media.Image
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import  android.widget.ImageButton
import com.example.ricky.misoficios.Modelos.Grupos
import com.example.ricky.misoficios.Modelos.Gruposrv
import com.example.ricky.misoficios.Modelos.nickname
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import kotlinx.android.synthetic.main.itm_grupos.view.*
import retrofit2.Callback
import retrofit2.Response
class AdapterGrupos(var list: ArrayList<Gruposrv>) : RecyclerView.Adapter<AdapterGrupos.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_grupos, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }
class ViewHolder(view: View):RecyclerView.ViewHolder(view){
    fun bindItems(data:Gruposrv){
        val nombre: TextView = itemView.findViewById(R.id.nombre)
        val usuarioActualizacion: TextView = itemView.findViewById(R.id.usuarioActualizacion)
        val propietario: TextView = itemView.findViewById(R.id.propietario)
        val actualizacion: TextView = itemView.findViewById(R.id.actualizacion)
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
        api.getNickName(data.IdUsuarioActualizacion!!)
                .enqueue(object: Callback<nickname>{
                override fun onResponse(call: retrofit2.Call<nickname>, response: Response<nickname>) {
                    d("Response recibido", "onResponse: ${response.body()}")
                    usuarioActualizacion.text = response.body().toString()
                }
                override fun onFailure(call: retrofit2.Call<nickname>, t: Throwable) {
                   d("Grupos:", "Falló el response")
                }
            })
        nombre.text = data.Nombre
        usuarioActualizacion.text = data.IdUsuarioActualizacion
        propietario.text = data.idUsuarioPropietario
        actualizacion.text = data.FechaActualizacion
       // propietarios.text = data
        //fecha.text = data.fecha
        }
}
}


