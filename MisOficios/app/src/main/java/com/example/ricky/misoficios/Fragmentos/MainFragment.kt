package com.example.ricky.misoficios.Fragmentos

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.ricky.misoficios.Modelos.Oficios
// import com.example.ricky.misoficios.Modelos.buildOficios
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {
    lateinit var dialog: AlertDialog
    lateinit var oficiosRecycler : RecyclerView
    lateinit var  oficiosList : ArrayList<Oficios>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.main_fragment, container, false)
        oficiosRecycler = view.findViewById(R.id.oficiosRecycler)
        var swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)
        val llm : LinearLayoutManager = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        oficiosRecycler.layoutManager = llm


        return view
    }

    fun onActualizarLista() {
        dialog.show()
        var usuario = SharedPreference.getInstance(context!!).usuario
        RetrofitClient.instance.getDocumentos("f65c506e-a975-4b96-854a-5bbbf42bb3d9","1","19")
            .enqueue(object : Callback<List<Oficios>> {
                override fun onFailure(call: Call<List<Oficios>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                    if (dialog.isShowing())
                        dialog.dismiss()
                }

                override fun onResponse(call: Call<List<Oficios>>, response: Response<List<Oficios>>) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            if (dialog.isShowing())
                                dialog.dismiss()
                            val adapter = AdapterOficios(buildOficios(response.body()!!),fragmentManager)
                            oficiosRecycler.adapter = adapter
                        } else {
                            if (dialog.isShowing())
                                dialog.dismiss()
                        }
                    }
                }

            })
    }

    fun buildOficios(Oficios: List<Oficios>) : ArrayList<Oficios>{

        oficiosList = ArrayList()
        for (item in Oficios){
            oficiosList.add(
                Oficios(
                    item.asunto,
                    item.remitente,
                    item.destinatario,
                    item.fecha,
                    item.folio,
                    item.codigo,
                    item.importancia
                )
            )

        }
        return oficiosList
    }






/*

 // Aquí toma los oficios de la lista

    val adapter = AdapterOficios(buildOficios())

    oficiosRecycler.adapter = adapter
*/


}
