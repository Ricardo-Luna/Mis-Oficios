package com.example.ricky.misoficios.adaptador


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Fragmentos.MainFragment
import com.example.ricky.misoficios.Fragmentos.lista_usuarios
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterCarpetas(
    var rv: RecyclerView,
    var list: ArrayList<Carpetas>,
    val context: Context,
    var activity: AppCompatActivity
) :
    RecyclerView.Adapter<AdapterCarpetas.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itm_carpetas, parent, false)
        return ViewHolder(view, activity)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }

    inner class ViewHolder(view: View, val activity: AppCompatActivity) :
        RecyclerView.ViewHolder(view) {


        fun bindItems(data: Carpetas) {
            val tvCarpetas: TextView = itemView.findViewById(R.id.tvCarpetas)
            val id = data.IdCarpeta
            val aux = data.Recibidos
            val nom = data.Nombre
            val carpeta = data.Nombre

            val globvar = MisOficios()
            globvar.usuarioId = data.IdUsuarioPropietario!!
            //val nuevoFragmento = MainFragment()
            //nuevoFragmento.recibirDatos(id!!,nom!!)
            tvCarpetas.text = data.Nombre
            globvar.setCarpetaSeleccionada(id!!)
            tvCarpetas.setOnClickListener {
                mostrarDocumentos(id)
                (activity).supportActionBar?.title = carpeta
            }
        }

        var usuario = SharedPreference.getInstance(context).usuario

        fun mostrarDocumentos(id: String) {
            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
            api.getDocsCarpetas(usuario.IdUsuario.toString(), id)
                .enqueue(object : Callback<List<Documentos>> {
                    override fun onResponse(
                        call: Call<List<Documentos>>,
                        response: Response<List<Documentos>>
                    ) {
                        if (response.isSuccessful) {
                            if (!response.body().isNullOrEmpty()) {
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

