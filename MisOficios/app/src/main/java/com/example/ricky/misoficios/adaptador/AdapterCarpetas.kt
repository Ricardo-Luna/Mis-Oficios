package com.example.ricky.misoficios.adaptador


import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.Fragmentos.MainFragment
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterCarpetas(
    var rv: RecyclerView,
    var list: ArrayList<Carpetas>,
    val context: Context
) :
    RecyclerView.Adapter<AdapterCarpetas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_carpetas, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        fun bindItems(data: Carpetas) {
            val tvCarpetas: TextView = itemView.findViewById(R.id.tvCarpetas)
            val id = data.IdCarpeta


            tvCarpetas.text = data.Nombre

            tvCarpetas.setOnClickListener { view ->


                if (data.Recibidos == true) {
                    mostrarDocumentos(id!!)
                    Log.d("--//Recycler: ", "recibidos")

                }
                if (data.Enviados == false) {
                    mostrarDocumentos(id!!)
                    Log.d("--//Recycler: ", "enviados")

                }
                if (data.Borradores == false) {
                    mostrarDocumentos(id!!)
                    Log.d("--//Recycler: ", "borradores")


                }

            }

        }


        fun mostrarDocumentos(id: String) {

            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
            api.getDocsCarpetas("b3be6e2f-7e79-474c-9985-fab45ed8956a", id)
                .enqueue(object : Callback<List<Documentos>> {
                    override fun onResponse(
                        call: Call<List<Documentos>>,
                        response: Response<List<Documentos>>
                    ) {
                        if (response.isSuccessful) {
                            if (!response.body().isNullOrEmpty()) {
                                Log.d(
                                    "Response recibido",
                                    "onResponse: ${response.body()!![0].Titulo}"
                                )
                                val Documentos = response.body()
                                val adapter = AdapterOficios(buildOficios(Documentos!!))
                                rv.adapter = adapter
                            } else {
                                Log.d("Response Oficios:", "recibido vacío")
                                Toast.makeText(
                                    context,
                                    "Sin documentos para mostrar",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                        Log.e("onFailure", t.message)

                    }
                }
                )
        }

        fun buildOficios(G: List<Documentos>): ArrayList<Oficios> {
            lateinit var oficiosList: ArrayList<Oficios>
            oficiosList = ArrayList()
            for (item in G) {
                oficiosList.add(
                    Oficios(
                        item.IdDocumento,
                        item.Titulo,
                        item.FechaEnvio,
                        item.IdPropietario,
                        item.idDocumentoRemitente,
                        item.IdCarpeta,
                        item.Codigo,
                        item.Importancia,
                        item.estatus,
                        item.PropietarioNombreCompleto

                    )
                )
            }
            return oficiosList
        }

    }


}

