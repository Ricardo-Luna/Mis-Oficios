package com.example.ricky.misoficios.Callables

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Fragmentos.MainFragment
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.Documentos2
import com.example.ricky.misoficios.Modelos.Oficios2
import com.example.ricky.misoficios.Modelos.folder
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitMethods {
    lateinit var oficiosRecycler: RecyclerView
    lateinit var  carpetasRecycler: RecyclerView
    lateinit var oficiosList: ArrayList<Oficios2>
    lateinit var carpetasList: ArrayList<Carpetas>
    lateinit var  context: Context


     fun onActualizarLista(context: Context) {
        var usuario = SharedPreference.getInstance(context).usuario
        RetrofitClient.instance.getDocumentos(//usuario.IdUsuario <-Este es el usuario de la aplicación,
            // que se puede intercambiar por la línea siguiente
            "ae10550a-cf5c-4912-aed6-3b0adbcde508"    //  <----
        )
            .enqueue(object : Callback<List<Documentos2>> {
                override fun onFailure(call: Call<List<Documentos2>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                }

                override fun onResponse(call: Call<List<Documentos2>>, response: Response<List<Documentos2>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {

                            val Documentos2 = response.body()
                            val adapter = AdapterOficios(buildOficios(Documentos2!!))
                            oficiosRecycler.adapter = adapter
                        }
                    }
                }

            })

    }




   public fun buildOficios(G: List<Documentos2>): ArrayList<Oficios2> {
        oficiosList = ArrayList()
        for (item in G) {
            oficiosList.add(
                Oficios2(
                    item.IdDocumento,
                    item.Titulo,
                    item.IdPropietario,
                    item.idDocumentoRemitente,
                    item.IdCarpeta,
                    item.Codigo,
                    item.Importancia,
                    item.estatus,
                    item.FechaEnvio,
                    item.PropietarioNombreCompleto

                )
            )
        }
        return oficiosList
    }














    fun buildCarpetas(G: List<folder>): ArrayList<Carpetas>{
        carpetasList = ArrayList()
        for(item in G){
            carpetasList.add(
                Carpetas(
                    item.IdCarpeta,
                    item.IdUsuarioPropietario,
                    item.Nombre,
                    item.Recibidos,
                    item.Enviados,
                    item.Borradores,
                    item.IdUsuarioActualizacion,
                    item.FechaActualizacion
                )
            )
        }
        return  carpetasList
    }

}