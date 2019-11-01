package com.example.ricky.misoficios.Fragmentos
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.Window
import com.example.ricky.misoficios.Modelos.Remitente
import com.example.ricky.misoficios.Modelos.Remitentes
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.adapterVistos
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import kotlinx.android.synthetic.main.dialog_views.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class dialogVistos(context: Context):Dialog(context) {
    init {
        setCancelable(false)

    }
    lateinit var vistosList: ArrayList<Remitente>
    lateinit var rv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_views)
        val llm = LinearLayoutManager(context)
        rv = findViewById(R.id.rvVistos)
        rv.layoutManager = llm

        mostrarRemitentes("4ee47204-fd96-4d10-8e3d-bc4baa2af5cc")
    }
    var x = 0
    fun mostrarRemitentes(id: String) {
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)

        api.getRemitentes(id)
            .enqueue(object : Callback<List<Remitentes>> {
                override fun onResponse(
                    call: Call<List<Remitentes>>,
                    response: Response<List<Remitentes>>
                ) {
                    val datos = response.body()
                    val adapter = adapterVistos(buildVistos(datos!!))
                    rv.adapter = adapter
                    var us = response.body()!![x].UsuarioNombreCompleto
                    var fecha = response.body()!![x].FechaLectura
                    x++
                    Log.d("Usuario en $x", "$us")
                    Log.d("Fecha en $x", "$fecha")
                }

                override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
                    Log.d("Mostrar documento", "Hubo un error")
                }
            })
    }

    fun buildVistos(G: List<Remitentes>): ArrayList<Remitente>{
        vistosList = ArrayList()
        for (item in G){
            vistosList.add(
                Remitente(
                    item.UsuarioNombreCompleto,
                    item.FechaLectura
                )
            )
        }
        return  vistosList
    }
}