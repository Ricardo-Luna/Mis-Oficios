package com.example.ricky.misoficios.Modelos

import android.content.Context
import android.graphics.Color
import com.example.ricky.misoficios.Almacenado.SharedPreference
import com.example.ricky.misoficios.MainActivity
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import com.example.ricky.misoficios.servicios.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.security.AccessController.getContext

lateinit var oficios : ArrayList<Oficios>
lateinit var grupos: ArrayList<Grupos>
lateinit var empleado : ArrayList<Empleados>
lateinit var empleadosGrupo: ArrayList<EmpleadosGrupo>
lateinit var leidos: ArrayList<Remitente>

//fun buildRemitentes(IdDocumento: String): ArrayList<Remitente>{
//    var usuario = SharedPreference.getInstance(MainActivity()).usuario
//    val api = RetrofitClient.retrofit.create(MisOficiosAPI::class.java)
//    api.getDestinatariosLeido(usuario.IdUsuario.toString(), IdDocumento)
//        .enqueue(object : Callback<List<Remitentes>> {
//            override fun onResponse(
//                call: Call<List<Remitentes>>,
//                response: Response<List<Remitentes>>
//            ) {
//                   var cont = 0
//                    val res = response.body()
//                    val fe =  res!![0].FechaLectura
//                    leidos = ArrayList()
//
//            }
//            override fun onFailure(call: Call<List<Remitentes>>, t: Throwable) {
//                println("FALLO EN LA LLAMADA")
//            }
//        })
//}

//fun buildOficios() : ArrayList<Oficios>{
//    oficios = ArrayList()
//    oficios.add(Oficios("Revisar app", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018", "000/2019",2 ))
//    oficios.add(Oficios("Urgente", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018" ,"01/2019",1))
//    oficios.add(Oficios("Mock data", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018","02/2019",2 ))
//    oficios.add(Oficios("Bottom Text", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018" ,"03/2019",1))
//    oficios.add(Oficios("Kilroy was here", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018" ,"04/2019",2))
//    oficios.add(Oficios("Sin asunto", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018" ,"05/2019",1))
//    oficios.add(Oficios("Publicar de a huevo", "Ricardo del pasado", "yo mero", "miercoles 19 de diciembre de 2018 1:25:00 p. m.", "9999/2018","06/2019",2 ))
//
//    return oficios
//}
//
//fun buildGrupos() : ArrayList<Grupos>{
//    grupos = ArrayList()
//    grupos.add(Grupos("Grupo 1", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 2", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 3", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 4", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 5", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 6", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 7", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//    grupos.add(Grupos("Grupo 8", "Rodolfo", "Ricardo Luna", "10:30 AM 9 de abril" ))
//
//    return grupos
//}

fun buildEmpleado(): ArrayList<Empleados>{
    empleado = ArrayList()
    empleado.add(Empleados("Ricardo", "Asistente"))
    empleado.add(Empleados("Eduardo", "Tester"))
    empleado.add(Empleados("Adrián", "Jefe"))
    empleado.add(Empleados("Marco", "Developer"))
    empleado.add(Empleados("Alejandro", "Estudiante"))
    empleado.add(Empleados("Fernando", "Asistente"))

    return empleado
}

fun buildEmpleadosGrupo(): ArrayList<EmpleadosGrupo>{
    empleadosGrupo = ArrayList()
    empleadosGrupo.add(EmpleadosGrupo("Martin,","Empleado"))
    empleadosGrupo.add(EmpleadosGrupo("Jose","Obras"))
    empleadosGrupo.add(EmpleadosGrupo("Abraham","Tesorero"))
    empleadosGrupo.add(EmpleadosGrupo("Carlos","Presidente"))

    return empleadosGrupo
}
