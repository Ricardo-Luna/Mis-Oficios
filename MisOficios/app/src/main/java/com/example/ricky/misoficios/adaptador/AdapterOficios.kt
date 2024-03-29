package com.example.ricky.misoficios.adaptador

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.Fragmentos.mostrarDocumento
import com.example.ricky.misoficios.MainActivity
import com.example.ricky.misoficios.Modelos.*
import com.example.ricky.misoficios.R
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import java.lang.Exception
import java.text.SimpleDateFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*
import kotlin.collections.ArrayList


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

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Oficios) {
            //Declaración de los TextViews y otros items visuales///////////////////////
            lateinit var hash: String
            lateinit var hs: String
            val asunto: TextView = itemView.findViewById(R.id.Asunto)
            val remitente: TextView = itemView.findViewById(R.id.Remitentes)
            val fecha: TextView = itemView.findViewById(R.id.Fecha)
            val folio: TextView = itemView.findViewById(R.id.Folio)
            val imagenMensaje: ImageView = itemView.findViewById(R.id.imageImportancia)
            val iv: ImageView = itemView.findViewById(R.id.imageRecibido)
            val ivv: ImageView = itemView.findViewById(R.id.imageLeido)
            val txleido: TextView = itemView.findViewById(R.id.txtLeido)
            val bm: CardView = itemView.findViewById(R.id.backgr)
            val tipo: ImageView = itemView.findViewById(R.id.imgTipo)
            val constraint: ConstraintLayout = itemView.findViewById(R.id.cns)
            // println("IdUsuario: ${data.IdPropietario}, IdCarpeta: ${data.IdCarpeta}, IdDocumento: ${data.IdDocumento}")
            //id del usuario y colores estánadares del ayuntamiento
            var usuario = SharedPreference.getInstance(itemView.context).usuario
            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
            val blanco = "#FFFFFF"
            val pantone = "#00b0e1"

            ///////////////////////////////////////////////////////////////////////////

            remitente.text = data.Destinatarios
            asunto.text = data.Titulo
            folio.text = data.Codigo

            ///////////////////////////////////////////////////////////////////////////

            try {
                hash = data.cadenaOriginal!! + data.IdPropietario //usuario.IdUsuario
                hs = hash.md5()
             //   println("Hash de ${data.Titulo}: $hs")
            } catch (e: Exception) {
              //  println("Cadena original vacia en ${data.idDocumentoRemitente}")
            }

            if (data.Tipo == "1") {
                tipo.setImageResource(R.drawable.borrador)
                tipo.setOnClickListener { view ->
                    Snackbar.make(view, "Borrador", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            if (data.Tipo == "2") {
                tipo.setImageResource(R.drawable.oficio)
                tipo.setOnClickListener { view ->
                    Snackbar.make(view, "Oficio", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            if (data.Tipo == "3") {
                tipo.setImageResource(R.drawable.copia)
                tipo.setOnClickListener { view ->
                    Snackbar.make(view, "Copia Digital", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus == "1") {
                iv.setImageResource(R.drawable.nw)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Nuevo", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus == "2") {
                iv.setImageResource(R.drawable.enviado)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Enviado", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            if (data.estatus == "3" || data.estatus == "4") {
                iv.setImageResource(R.drawable.recibido)
                iv.setOnClickListener { view ->
                    Snackbar.make(view, "Recibido", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            ivv.setOnClickListener { view ->
                Snackbar.make(view, "Leído", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
            println("Titulo: ${data.Titulo} estatus de documento: ${data.estatus}, tipo de documento: ${data.Tipo}")

            ///////////////////////////////////////////////////////////////////////////
          //  println("ID: ${data.IdDocumento},TITULO: ${data.Titulo},ID REM: ${data.idDocumentoRemitente}")
            api.getDestinatariosLeido(usuario.IdUsuario.toString(), data.idDocumentoRemitente!!)
                .enqueue(object : Callback<List<Remitentes>> {
                    override fun onResponse(
                        call: Call<List<Remitentes>>,
                        response: Response<List<Remitentes>>
                    ) {
                        try {
                            val res = response.body()
                            val fe = res!![0].FechaLectura
                            //per println("LEIDO: $fe")
                            if (fe == null || fe == "") {
                                // println("Asunto = ${data.Titulo}, Fecha de lectura  = +$fe+, Position ${adapterPosition}")
                                asunto.setTextColor(Color.parseColor(blanco))
                                remitente.setTextColor(Color.parseColor(blanco))
                                fecha.setTextColor(Color.parseColor(blanco))
                                folio.setTextColor(Color.parseColor(blanco))
                                constraint.setBackgroundColor(Color.parseColor(pantone))
                                if (data.Tipo == "2") {
                                    tipo.setImageResource(R.drawable.z_oficio)
                                    tipo.setOnClickListener { view ->
                                        Snackbar.make(view, "Oficio", Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show()
                                    }
                                }
                                if (data.Tipo == "3") {
                                    tipo.setImageResource(R.drawable.z_copia)
                                    tipo.setOnClickListener { view ->
                                        Snackbar.make(view, "Copia Digital", Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show()
                                    }
                                }

                                if (data.estatus == "1") {
                                    iv.setImageResource(R.drawable.nw)
                                    iv.setOnClickListener { view ->
                                        Snackbar.make(view, "Nuevo", Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show()
                                    }
                                }

                                if (data.estatus == "2") {
                                    iv.setImageResource(R.drawable.z_enviado)
                                    iv.setOnClickListener { view ->
                                        Snackbar.make(view, "Enviado", Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show()
                                    }
                                }
                                if (data.estatus == "3") {
                                    iv.setImageResource(R.drawable.z_recibido)
                                    iv.setOnClickListener { view ->
                                        Snackbar.make(view, "Recibido", Snackbar.LENGTH_SHORT)
                                            .setAction("Action", null).show()
                                    }
                                }
                                ivv.setImageResource(R.drawable.z_no_leido)
                                ivv.setOnClickListener { view ->
                                    Snackbar.make(view, "No leído", Snackbar.LENGTH_SHORT)
                                        .setAction("Action", null).show()
                                }
                                txleido.setText(".")
                                if (data.Importancia == "1") {
                                    imagenMensaje.setImageResource(R.drawable.z_importancia_baja)
                                }
                                if (data.Importancia == "2") {
                                    imagenMensaje.setImageResource(R.drawable.z_importancia_normal)
                                }
                                if (data.Importancia == "3") {
                                    imagenMensaje.setImageResource(R.drawable.z_importancia_alta)
                                }
                            }
                            if (fe != "") {
                                // println("Asunto = ${data.Titulo}, Fecha de lectura  = +$fe!!+, Posision ${adapterPosition}")
                            }
                        } catch (e: Exception) {

                        }
                    }
                    override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
                        println("FALLO EN LA LLAMADA")
                    }
                })

            //Adapta la fecha de envío al formato amigable para el usuario///////////////
            if (data.FechaCreacion != null) {
                try {
                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    val formatter = SimpleDateFormat("HH:mm, dd/MM/yyyy")
                    val formattedDate = formatter.format(parser.parse(data.FechaCreacion))
                    fecha.text = formattedDate
                } catch (e: Exception) {
                    fecha.text = data.FechaCreacion
                }
            }




            /////////////////////////////////////////////////////////////////////////////
            bm.setOnClickListener {
                val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
                api.getDocumentoHtml(data.IdDocumento!!)
                    .enqueue(object : Callback<Oficio> {
                        override fun onResponse(call: Call<Oficio>, response: Response<Oficio>) {
                            try {
                                val tx = response.body()
                                print("Doc creado:  ${tx?.ContenidoHTML.toString()}")
                                createDocText(tx?.ContenidoHTML.toString()+".")
                                val date = Date()
                                val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                val answer: String = formatter.format(date)
                  //              println("answer $answer")
                  //              println("Cuerpo ${response.body()!!.ContenidoHTML}")
                                if (txleido.text == ".") {
                                    marcarVisto(answer, data.idDocumentoRemitente!!)
                                }
                                val context: Context = itemView.context
                                val intent = Intent(context, mostrarDocumento::class.java)
                                intent.putExtra("Tipo", data.Tipo)
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                println(e.message)
                            }
                        }
                        override fun onFailure(call: Call<Oficio>, t: Throwable) {
                            println("Fallo porque $t")
                        }
                    })
            }
            /////////////////////////////////////////////////////////////////////////////

            // Asigna el ícono que se adapta a la importancia///////////////////////////
            if (data.Importancia?.toInt() == 1) {
                imagenMensaje.setImageResource(R.drawable.importancia_baja)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia baja", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }

            if (data.Importancia?.toInt() == 2) {
                imagenMensaje.setImageResource(R.drawable.importancia_normal)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia normal", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }

            if (data.Importancia?.toInt() == 3) {
                imagenMensaje.setImageResource(R.drawable.imporancia_alta)
                imagenMensaje.setOnClickListener { view ->
                    Snackbar.make(view, "Importancia alta", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                }
            }
            ////////////////////////////////////////////////////////////////////////////////
        }

        fun String.md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
        }

        fun createDocText(text: String) {
            try {
                val fileName = "/sdcard/ss2.html"
                var file = File(fileName)
                val isNewFileCreated: Boolean = file.createNewFile()
                if (isNewFileCreated) {
                    println("$fileName is created successfully.")
                } else {
                    println("$fileName already exists.")
                }
                // try creating a file that already exists
                val isFileCreated: Boolean = file.createNewFile()
                if (isFileCreated) {
                    println("$fileName is created successfully.")
                } else {
                    println("$fileName already exists.")
                }
                file.writeText(
                    text
                )
            } catch (e: Exception) {
                println("Fallo al crear archivo: $e")
            }
        }

        fun marcarVisto(fecha: String, id: String) {
            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
            var visto = Visto(fecha, id)
            api.setVisto(visto)
                .enqueue(object : Callback<Visto> {
                    override fun onResponse(call: Call<Visto>, response: Response<Visto>) {
                        println("Mierda agregada")
                    }

                    override fun onFailure(call: Call<Visto>, t: Throwable) {
                        println("Falla: $t")
                    }
                })
        }

        fun genHash(cadena: String, id: String) {
            val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
            val hashAx = hash(cadena, id)
            api.genHash(hashAx)
                .enqueue(object : Callback<hash> {
                    override fun onResponse(call: Call<hash>, response: Response<hash>) {
                        val res = response.message()
                        println("Mensaje: $res")
                    }

                    override fun onFailure(call: Call<hash>, t: Throwable) {
                        println("Error en la solicitud: $t")
                    }
                })
        }


    }
}

//-------------------------------------------------------------------------------------------------------------------------
//private fun ShowDialog() {
//    val mDialogView =
//        LayoutInflater.from(MainFragment().context!!).inflate(R.layout.dialog_views, null)
//    val mBuilder = AlertDialog.Builder(MainActivity())
//        .setView(mDialogView)
//    //.setTitle("Firmar")
//    val mAlertDialog = mBuilder.show()
//    mDialogView.buttonOkViews.setOnClickListener()
//    {
//        mAlertDialog.dismiss()
//    }
//    // val firmar = mDialogView.findViewById<Button>(R.id.Firmar).toString()
//}
//

//     fun callDocHtml(id: String) {
//         val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
//         api.getDocumentoHtml(id)
//             .enqueue(object : Callback<Oficio> {
//                 override fun onResponse(call: Call<Oficio>, response: Response<Oficio>) {
//                     try {
//                         //context = MainActivity().baseContext
//
//                         val tx = response.body()
//                         print("Doc creado:  ${tx?.ContenidoHTML.toString()}")
//                         createDocText(tx?.ContenidoHTML.toString())
//                         // val intent = Intent(getContext()!!, mostrarDocumento::class.java)
//                         // startActivity(intent)
//                     } catch (e: Exception) {
//                         println(e.message)
//                     }
//                     //MainActivity().run {
//                     //    startActivity(Intent(this, mostrarDocumento::class.java))
//                     //    finish()
//                     //
//                     //}
//                 }
//
//                 override fun onFailure(call: Call<Oficio>, t: Throwable) {
//                 }
//             })
//     }
//
//     fun createDocText(text: String) {
//         try {
//             val fileName = "/sdcard/ss2.html"
//             var file = File(fileName)
//             val isNewFileCreated: Boolean = file.createNewFile()
//             if (isNewFileCreated) {
//                 println("$fileName is created successfully.")
//             } else {
//                 println("$fileName already exists.")
//             }
//             // try creating a file that already exists
//             val isFileCreated: Boolean = file.createNewFile()
//             if (isFileCreated) {
//                 println("$fileName is created successfully.")
//             } else {
//                 println("$fileName already exists.")
//             }
//             file.writeText(
//                 text
//             )
//         } catch (e: Exception) {
//             println("Fallo al crear archivo: $e")
//         }
//     }
//
// }
//////////////////////////////////////////////////////////////////////
//fun comprobarLeido(idDestinatario: String, idDocumento: String,cns: ConstraintLayout) {
//    val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
// api.getDestinatariosLeido(idDestinatario, idDocumento)
//     .enqueue(object : Callback<List<Remitentes>> {
//         override fun onResponse(
//             call: Call<List<Remitentes>>,
//             response: Response<List<Remitentes>>
//         ) {
//
//             val rs = response.body()
//             println("IDP: $idDestinatario, IDD: $idDocumento, ")
//             if (rs!![1].FechaLectura == null||rs[1].FechaLectura =="") {
//                 cns.setBackgroundColor(Color.CYAN)
//             }
//         }
//
//         override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
//             TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//         }
//     })

//}
//fun comprobarVisto(
//    tx1: TextView, tx2: TextView, tx3: TextView, imageView: ImageView,
//    imageView2: ImageView, imageView3: ImageView, cns: ConstraintLayout
//) {
//    val blanco = "#FFFFFF"
//    tx1.setTextColor(Color.parseColor(blanco))
//    tx2.setTextColor(Color.parseColor(blanco))
//    tx3.setTextColor(Color.parseColor(blanco))
//}

//fun mostrarVistos()//id: String)
//{
//    val dl = AlertDialog.Builder(MainActivity())
//    ShowDialog()
//}
//Llama a la API y solicita los remitentes de cierto documento, recibe el id del documento y el textview de remitentes
//  fun mostrarDestinatarios(id: String, tx: TextView) {
//      val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
//      api.getRemitentes(id)
//          .enqueue(object : Callback<List<Remitentes>> {
//              override fun onResponse(
//                  call: Call<List<Remitentes>>,
//                  response: Response<List<Remitentes>>
//              ) {
//                  val tam = response.body()?.size
//                  //d("Cantidad Rems: ", tam.toString())
//
//                  if (tam!!.equals(0)) {
//                      aux = ""
//                      // tx.text  = response.body()!![0].UsuarioNombreCompleto
//                      try {
//                          tx.text = response.body()!![0].UsuarioNombreCompleto
//                      } catch (e: Exception) {
//                          //  d("-/Remitente : ", "Algo falló")
//                      }
//
//                  }
//                  if (tam!!.equals(1)) {
//                      aux = ""
//                      val nom1 = "PARA " + response.body()!![0].UsuarioNombreCompleto
//                      aux = nom1
//                      tx.text = aux
//                      //    d("-/Remitente : ", aux)
//                      // return aux
//
//                  }
//                  if (tam.equals(2)) {
//                      aux = ""
//                      val nom1 = "PARA " + response.body()!![0].UsuarioNombreCompleto
//                      val nom2 = response.body()!![1].UsuarioNombreCompleto
//                      aux = nom1 + ", " + nom2
//                      tx.text = aux
//                      //   d("-//Remitente : ", aux)
//                      //return aux
//                  }
//                  if (tam > 2) {
//                      aux = ""
//                      aux = "Para " + tam.toString() + " remitentes"
//                      //    d("-///Remitente : ", aux)
//                      tx.text = aux
//                      //  return aux
//                  }
//              }
//
//              override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
//                  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//              }
//          })
//  }
//----------------------------------------------------------------------------------------------------------------------
//fun mostrarRemitentes(id: String, tx: TextView) {
//    val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
//    api.getNickName(id)
//        .enqueue(object : Callback<List<nickname>> {
//            override fun onResponse(
//                call: Call<List<nickname>>,
//                response: Response<List<nickname>>
//            ) {
//                val rs = response.body()
//                val ax = rs!![0].NombreCompleto
//                tx.text = ax
//            }
//
//            override fun onFailure(call: Call<List<nickname>>, t: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//        })
//}


/////////////////////////////////////////////////////////////////////
//api.getDestinatariosLeido(usuario.IdUsuario.toString(), data.IdDocumento!!)
//.enqueue(object : Callback<List<Remitentes>> {
//    override fun onResponse(
//        call: Call<List<Remitentes>>,
//        response: Response<List<Remitentes>>
//    ) {
//        try {
//            val rs = response.message()
//            val res = response.body()
//            val fe = "+ " + res!![0].FechaLectura
//            //per println("LEIDO: $fe")
//            if (fe == null) {
//                //  d("LEIDO", "EXITO EN LA LLAMADA//")
//                // asunto.setTextColor(Color.parseColor(blanco))
//                // remitente.setTextColor(Color.parseColor(blanco))
//                // fecha.setTextColor(Color.parseColor(blanco))
//                // folio.setTextColor(Color.parseColor(blanco))
//                // constraint.setBackgroundColor(Color.parseColor(pantone))
//            }
//            if (fe != "") {
//                //  d("LEIDO: ", "$fe")
//            }
//        } catch (e: Exception) {
//            asunto.setTextColor(Color.parseColor(blanco))
//            remitente.setTextColor(Color.parseColor(blanco))
//            fecha.setTextColor(Color.parseColor(blanco))
//            folio.setTextColor(Color.parseColor(blanco))
//            constraint.setBackgroundColor(Color.parseColor(pantone))
//        }
//    }
//    override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
//        //     d("ERRROR", "FALLO EN LA LLAMADA")
//    }
//})

// .enqueue(object : Callback<nickname> {
//     override fun onResponse(call: Call<nickname>, response: Response<nickname>) {
//             //           val jsonarray = JSONArray(response)
//
//         val rs = response.body()
//         rs.toString()
//         d("REMITENTES", "${rs}")
//     }
//
//     override fun onFailure(call: Call<nickname>, t: Throwable) {
//         d("ERROR", "NO REMITENTES")
//     }
// }
// constraint.setOnClickListener { view ->
//     constraint.setBackgroundColor(Color.WHITE)
//     asunto.setTextColor(Color.BLACK)
//     remitente.setTextColor(Color.BLACK)
//     fecha.setTextColor(Color.BLACK)
//     folio.setTextColor(Color.BLACK)
//
// }

//  if (data.estatus?.toInt() == 1) {
//      if (data.IdPropietario !== usuario.IdUsuario.toString()) {
//          asunto.setTextColor(Color.parseColor(blanco))
//          remitente.setTextColor(Color.parseColor(blanco))
//          fecha.setTextColor(Color.parseColor(blanco))
//          folio.setTextColor(Color.parseColor(blanco))
//          constraint.setBackgroundColor(Color.parseColor(pantone))
//      }
//      iv.setImageResource(R.drawable.nuevo)
//      iv.setOnClickListener { view ->
//          Snackbar.make(view, "Nuevo Documento", Snackbar.LENGTH_SHORT)
//              .setAction("Action", null).show()
//          mostrarVistos()
//      }
//  }
//  if (data.estatus?.toInt() == 2) {
//      if (data.IdPropietario !== usuario.IdUsuario.toString()) {
//          asunto.setTextColor(Color.parseColor(blanco))
//          remitente.setTextColor(Color.parseColor(blanco))
//          fecha.setTextColor(Color.parseColor(blanco))
//          folio.setTextColor(Color.parseColor(blanco))
//          constraint.setBackgroundColor(Color.parseColor(pantone))
//      }
//      iv.setImageResource(R.drawable.z_enviado)
//      iv.setOnClickListener { view ->
//          Snackbar.make(view, "Documento enviado", Snackbar.LENGTH_SHORT)
//              .setAction("Action", null).show()
//      }
//  }
//  if (data.estatus?.toInt() == 3) {
//      if (data.IdPropietario !== usuario.IdUsuario.toString()) {
//          asunto.setTextColor(Color.parseColor(blanco))
//          remitente.setTextColor(Color.parseColor(blanco))
//          fecha.setTextColor(Color.parseColor(blanco))
//          folio.setTextColor(Color.parseColor(blanco))
//          constraint.setBackgroundColor(Color.parseColor(pantone))
//      }
//      iv.setImageResource(R.drawable.z_recibido)
//      iv.setOnClickListener { view ->
//          Snackbar.make(view, "Recibido", Snackbar.LENGTH_SHORT)
//              .setAction("Action", null).show()
//      }
//  }
//  if (data.estatus?.toInt() == 4) {
//      ivv.setImageResource(R.drawable.leido)
//
//      ivv.setOnClickListener { view ->
//          Snackbar.make(view, "Leído", Snackbar.LENGTH_SHORT)
//              .setAction("Action", null).show()
//      }
//  }
//  if (data.estatus?.toInt() == 5) {
//      ivv.setImageResource(R.drawable.deleted)
//      ivv.setOnClickListener { view ->
//          Snackbar.make(
//              view,
//              "Jajja we esto ni siquiera debería salir",
//              Snackbar.LENGTH_LONG
//          )
//              .setAction("Action", null).show()
//      }
//  }


//596
