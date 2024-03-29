package com.example.ricky.misoficios.Fragmentos

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ricky.misoficios.Almacenado.DBHelper
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Almacenado.dbOficios
import com.example.ricky.misoficios.Modelos.Carpetas
import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.Modelos.folder
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.adaptador.AdapterCarpetas
import com.example.ricky.misoficios.adaptador.AdapterOficios
import com.example.ricky.misoficios.servicios.MisOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import com.example.ricky.misoficios.servicios.RetrofitClient.retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.concurrent.ExecutorCompletionService

class MainFragment : Fragment() {

    lateinit var oficiosRecycler: RecyclerView
    lateinit var carpetasRecycler: RecyclerView
    lateinit var oficiosList: ArrayList<Oficios>
    lateinit var carpetasList: ArrayList<Carpetas>
    var nombreCarpeta: String = "Recibidos"
    val globvar = MisOficios()

    //lateinit var id: String
    //val mydb =  DBHelper(MainActivity(), null)

    private lateinit var sheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private var listener: lista_usuarios.OnFragmentInteractionListener? = null

    //objeto de {servicios/RetrofitClient}
    val api = retrofit.create(MisOficiosAPI::class.java)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //val oficiosDb =  dbOficios(context!!,null)
        var usuario = SharedPreference.getInstance(context!!).usuario
        val view: View = inflater.inflate(R.layout.main_fragment, container, false)
        val carpetasSelected = getCarpetaInicial(usuario.IdUsuario!!)
        (activity as AppCompatActivity).supportActionBar?.title = nombreCarpeta
        oficiosRecycler = view.findViewById(R.id.oficiosRecycler)
        carpetasRecycler = view.findViewById(R.id.recyclerCarpetas)

        val swipeRefreshLayout: SwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        getCarpetaInicial(usuario.IdUsuario!!)
        onMostrarCarpetas(usuario.IdUsuario!!)
        mostrarDocumentos(usuario.IdUsuario!!, carpetasSelected)
        val llm = LinearLayoutManager(context)
        val llm2 = LinearLayoutManager(context)
        llm.orientation = LinearLayout.VERTICAL
        oficiosRecycler.layoutManager = llm
        carpetasRecycler.layoutManager = llm2

        try {
            val nombreusuario: TextView = view.findViewById(R.id.textviewNombreUsuario)
            nombreusuario.text = usuario.NombreCompleto
        } catch (e: Exception) {

        }

        // --Aquí alterno entre los métodos siguientes
//      --Método que actualiza el layout arrastándolo hacia abajo

        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipeRefreshLayout.setRefreshing(true)

            mostrarDocumentos(usuario.IdUsuario!!, carpetasSelected)

            swipeRefreshLayout.setRefreshing(false)
        })

        return view
    }

    fun mostrarDocumentos(id: String, carpeta: String) {
//        var usuario = SharedPreference.getInstance(context!!).idusuario
        api.getDocsCarpetas(
            id, carpeta
        )
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(
                    call: Call<List<Documentos>>,
                    response: Response<List<Documentos>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {

                            val Documentos = response.body()
                            var fin = response.body()?.size
                            val adapter = AdapterOficios(buildOficios(Documentos!!))
                            //      d("Tamaño de Response: ", fin.toString())
                            oficiosRecycler.adapter = adapter

                        } else {
                            //  d("Response Oficios:", "recibido vacío")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                    //  d("onResponse: ", "Response no recibido")
                }
            }
            )
    }

    fun buildOficios(G: List<Documentos>): ArrayList<Oficios> {
        oficiosList = ArrayList()
        for (item in G) {
            oficiosList.add(
                Oficios(
                    item.IdDocumento,
                    item.Titulo,
                    item.FechaEnvio,
                    item.FechaCreacion,
                    item.IdPropietario,
                    item.idDocumentoRemitente,
                    item.IdCarpeta,
                    item.Codigo,
                    item.Importancia,
                    item.estatus,
                    item.Destinatarios,
                    item.PropietarioNombreCompleto,
                    item.cadenaOriginal,
                    item.Tipo
                )
            )
        }
        return oficiosList
    }

    fun addToDatabase(id: String, carpeta: String) {
//        var usuario = SharedPreference.getInstance(context!!).idusuario
        api.getDocsCarpetas(
            id, carpeta
        )
            .enqueue(object : Callback<List<Documentos>> {
                override fun onResponse(
                    call: Call<List<Documentos>>,
                    response: Response<List<Documentos>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            val Documentos = response.body()
                            var fin = response.body()?.size
                        } else {
                            //   d("Response Oficios:", "recibido vacío")
                        }
                    }
                }

                override fun onFailure(call: Call<List<Documentos>>, t: Throwable) {
                    Log.e("onFailure", t.message)
                    //  d("onResponse: ", "Response no recibido")
                }
            }
            )
    }

    fun getIDUser(): String {
        val dbHandler = DBHelper(context!!, null)
        val bd = dbHandler.writableDatabase
        var id = ""
        val idb = bd.rawQuery("Select id from datos", null)
        //val carpetaRecibidos = bd.rawQuery("Select Carpeta_Recibidos from datos", null)
        if (idb.moveToFirst()) {
            id = idb.getString(0).toString()

        } else //d("XXXFRAGMENT", "Error")

            bd.close()
        globvar.usuarioId = id
        return id
    }

    fun getCarpetaInicial(id: String): String {
        val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
        var usuario = SharedPreference.getInstance(context!!).usuario
        var idrecibidos = ""
        api.getCarpetaRecibidos(id)
            .enqueue(object : Callback<List<Carpetas>> {
                override fun onResponse(
                    call: Call<List<Carpetas>>,
                    response: Response<List<Carpetas>>
                ) {
                    if (response.isSuccessful) {
                        val fold = response.body()!!
                        idrecibidos = fold[0].IdCarpeta.toString()
                        mostrarDocumentos(usuario.IdUsuario.toString(), idrecibidos)
                        //d("XYXCARPETARECIBIDOS: ", idrecibidos)

                    } else {
                        //  d("INGESU", "YA CHINGO ASU MARE COMARE")
                    }
                }

                override fun onFailure(call: Call<List<Carpetas>>, t: Throwable) {
                    //   d("INGESU", " THIS AINT FUNNY ANYMORE")
                }
            })
        // d("XYXCARPETARECIBIDOS: ", idrecibidos)
        return idrecibidos
    }

    fun onMostrarCarpetas(id: String) {
        RetrofitClient.instance.getCarpetas(id) //id)
            .enqueue(object : Callback<List<folder>> {
                override fun onResponse(
                    call: Call<List<folder>>,
                    response: Response<List<folder>>
                ) {
                    if (response.isSuccessful) {
                        if (!response.body().isNullOrEmpty()) {
                            val folder = response.body()
                            try {
                                val adapter2 = AdapterCarpetas(
                                    oficiosRecycler, buildCarpetas(folder!!)
                                    , context!!, activity as AppCompatActivity
                                )
                                carpetasRecycler.adapter = adapter2
                            }
                            catch (e: Exception)
                            {
                                println("Problema en $e")
                            }

                        } else {
                            Toast.makeText(context, "Sin carpetas para mostrar", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<List<folder>>, t: Throwable) {
                    //     d("USUARIO: onFailure: ", "Fallo en MainFragment")
                }

            })
    }

    // --Función para generar los oficios, que recibe una lista de tipo Documentos, cuya estructura está en Modelos/Oficios
    fun buildCarpetas(G: List<folder>): ArrayList<Carpetas> {
        carpetasList = ArrayList()
        for (item in G) {
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
        return carpetasList
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
