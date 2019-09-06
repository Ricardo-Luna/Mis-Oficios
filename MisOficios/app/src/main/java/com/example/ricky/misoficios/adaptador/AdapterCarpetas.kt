package com.example.ricky.misoficios.adaptador

import android.support.design.widget.Snackbar
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ricky.misoficios.Fragmentos.MainFragment
import com.example.ricky.misoficios.Modelos.Oficios2
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import kotlinx.android.synthetic.main.itm_carpetas.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdapterCarpetas(var list: ArrayList<Carpetas>):
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


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItems(data: Carpetas){
            val tvCarpetas: TextView = itemView.findViewById(R.id.tvCarpetas)
            tvCarpetas.text = data.Nombre
                tvCarpetas.setOnClickListener { view ->
                    Snackbar.make(view, "Leído", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            if(data.Recibidos == true )
            {

            }
            if(data.Enviados == true)
            {

            }
            if(data.Borradores == true)
            {

            }

        }
    }

    fun  mostrarDocumentos(){
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
        lateinit var oficiosRecycler: RecyclerView
       // oficiosRecycler = findViewById(R.id.oficiosRecycler)
        api.getDocsCarpetas("ae10550a-cf5c-4912-aed6-3b0adbcde508",    //  <----
            "8995969b-6837-46d2-bd91-485c4d3ee8c2")
            .enqueue(object: Callback<List<Documentos>> {
                override fun onResponse(call: Call<List<Documentos>>, response: Response<List<Documentos>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            Log.d("Response recibido", "onResponse: ${response.body()!![0].Titulo}")
                            val Documentos = response.body()
                            var fin = response.body()?.size
                            val adapter = AdapterOficios(buildOficios(Documentos!!))
                            oficiosRecycler.adapter = adapter
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

