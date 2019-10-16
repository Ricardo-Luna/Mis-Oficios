package com.example.ricky.misoficios.adaptador


import android.app.Activity
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.MainActivity
import com.example.ricky.misoficios.Modelos.Oficios
import com.example.ricky.misoficios.Modelos.Remitente
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficios
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import java.lang.Exception
import java.text.SimpleDateFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdapterOficios(var list: ArrayList<Oficios>) :
    RecyclerView.Adapter<AdapterOficios.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindItems(list[position])

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        lateinit var rems: String
        lateinit var ret: String
        fun bindItems(data: Oficios) {

            ret = mostrarRemitentes(data.IdDocumento!!)
            val asunto: TextView = itemView.findViewById(R.id.Asunto)
            val remitente: TextView = itemView.findViewById(R.id.Remitentes)
            val fecha: TextView = itemView.findViewById(R.id.Fecha)
            val folio: TextView = itemView.findViewById(R.id.Folio)
            val imagenMensaje: ImageView = itemView.findViewById(R.id.imageImportancia)
            val iv: ImageView = itemView.findViewById(R.id.imageRecibido)
            val ivv: ImageView = itemView.findViewById(R.id.imageLeido)
            val bm: CardView = itemView.findViewById(R.id.backgr)
            val constraint: ConstraintLayout = itemView.findViewById(R.id.cns)
            var usuario = SharedPreference.getInstance(itemView.context).usuario
            asunto.text = data.Titulo

            if (data.FechaEnvio != null) {
                try {
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

                    val formatter = SimpleDateFormat("HH:mm, dd/MM/yyyy")

                    val formattedDate = formatter.format(parser.parse(data.FechaEnvio))

                    fecha.text = formattedDate

                } catch (e: Exception) {
                    fecha.text = data.FechaEnvio
                }
            }

            folio.text = data.Codigo
            // d("PRUEBA", mostrarRemitentes(data.IdDocumento!!))

            remitente.text = ret

            if (data.estatus?.toInt() == 1) {
                if (data.IdPropietario != usuario.toString()) {
                    constraint.setBackgroundColor(Color.CYAN);
                    d("USUARIO: ", "DATA: ${data.IdPropietario}, USUARIO: $usuario")
                }

                iv.setImageResource(R.drawable.nuevo)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Nuevo Documento", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 2) {
                if (data.IdPropietario != usuario.toString()) {
                    constraint.setBackgroundColor(Color.CYAN);
                    d("USUARIO: ", "DATA: ${data.IdPropietario}, USUARIO: $usuario")
                }
                iv.setImageResource(R.drawable.enviado)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Documento enviado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 3) {
                if (data.IdPropietario != usuario.toString()) {
                    constraint.setBackgroundColor(Color.CYAN);
                    d("USUARIO: ", "DATA: ${data.IdPropietario}, USUARIO: $usuario")
                }
                iv.setImageResource(R.drawable.recibido)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Recibido", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus?.toInt() == 4) {
                ivv.setImageResource(R.drawable.leido)

                ivv.setOnClickListener { view ->
                    Snackbar.make(view, "Leído", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                    bm.setBackgroundResource(R.drawable.gradient)

                }
            }
            if (data.estatus?.toInt() == 5) {
                ivv.setImageResource(R.drawable.deleted)
                ivv.setOnClickListener { view ->
                    Snackbar.make(
                        view,
                        "Jajja we esto ni siquiera debería salir",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Action", null).show()
                }
            }


            if (data.Importancia?.toInt() == 1) {
                imagenMensaje.setImageResource(R.drawable.importancia_baja)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia baja", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }

            if (data.Importancia?.toInt() == 2) {
                imagenMensaje.setImageResource(R.drawable.importancia_normal)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia normal", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }

            if (data.Importancia?.toInt() == 3) {
                imagenMensaje.setImageResource(R.drawable.imporancia_alta)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia alta", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }
            }

        }

        fun mostrarRemitentes(id: String): String {
            var aux = ""
            val globvar = MisOficios()
            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)

            api.getRemitentes(id)
                .enqueue(object : Callback<List<Remitente>> {
                    override fun onResponse(
                        call: Call<List<Remitente>>,
                        response: Response<List<Remitente>>
                    ) {
                        val tam = response.body()?.size
                        d("Cantidad Rems: ", tam.toString())

                        if (tam!!.equals(1)) {
                            val nom1 = response.body()!![0].UsuarioNombreCompleto
                            aux = nom1
                            globvar.setCadena(aux)

                            d("-/Remitente : ", aux)
                        }

                        if (tam.equals(2)) {
                            val nom1 = response.body()!![0].UsuarioNombreCompleto
                            val nom2 = response.body()!![1].UsuarioNombreCompleto
                            aux = nom1 + ", " + nom2
                            globvar.setCadena(aux)
                            d("-//Remitente : ", aux)
                        }

                        if (tam > 2) {
                            aux = tam.toString() + " remitentes"
                            d("-///Remitente : ", aux)
                            globvar.setCadena(aux)
                        }

                    }

                    override fun onFailure(call: Call<List<Remitente>>, t: Throwable) {
                        d("Mostrar Remitentes", "Algo falló")
                    }
                })
            ret = globvar.getCadena()
            d("--Remitente : ", aux)

            return aux

        }
    }
}


