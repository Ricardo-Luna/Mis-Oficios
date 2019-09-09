package com.example.ricky.misoficios.adaptador


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

class AdapterCarpetas( var list: ArrayList<Carpetas>):
    RecyclerView.Adapter<AdapterCarpetas.ViewHolder>()
{

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


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {


        fun bindItems(data: Carpetas){
            val tvCarpetas: TextView = itemView.findViewById(R.id.tvCarpetas)
            val id = data.IdCarpeta
            var oficiosRecycler: RecyclerView
            tvCarpetas.text = data.Nombre
            try {
                tvCarpetas.setOnClickListener { view ->
                    oficiosRecycler = itemView.findViewById(R.id.oficiosRecycler)
                    Log.d("--//Recycler: ", "queonda")
                    if (data.Recibidos == true) {
                        mostrarDocumentos(id!!, oficiosRecycler)
                    }
                    if (data.Enviados == true) {
                        mostrarDocumentos(id!!, oficiosRecycler)
                    }
                    if (data.Borradores == true) {
                        mostrarDocumentos(id!!, oficiosRecycler)
                    }
                }
            }catch (e:Exception){Log.d("--//Recycler: ", "Excepción encontrada")}
        }
        fun  mostrarDocumentos(id:String,recyclerView: RecyclerView){
            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
            api.getDocsCarpetas("3f66d2e6-57cb-48cd-8632-21ec91f80421", "eb25d47c-cb26-44e6-bfb0-26e3e47c1dac")
                .enqueue(object: Callback<List<Documentos>> {
                    override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
                        if (response.isSuccessful) {
                            if (!response.body().isNullOrEmpty()) {
                                Log.d("Response recibido", "onResponse: ${response.body()!![0].Titulo}")
                                val Documentos = response.body()
                                val adapter = AdapterOficios(buildOficios(Documentos!!))
                                recyclerView.adapter = adapter
                               // oficiosRecycler = itemView.findViewById(R.id.oficiosRecycler,id)

                            }
                            else
                            {
                                Log.d("Response Oficios:", "recibido vacío")
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                        Log.e("onFailure", t.message)
                        Log.d("onResponse: ", "Response no recibido")
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

