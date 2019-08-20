package com.example.ricky.misoficios.servicios


import com.example.ricky.misoficios.Modelos.Documentos
import com.example.ricky.misoficios.Modelos.LoginReq
import com.example.ricky.misoficios.Modelos.LoginRes
import com.example.ricky.misoficios.Modelos.Oficios
import retrofit2.Call
import retrofit2.http.*

interface MisOficiosAPI {


    @GET("Documentos/{IdUsuarioPropietario}/{inicio}/{final}")
    fun getDocumentos(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String,
        @Path("inicio") inicio: String,
        @Path("final") final: String
    ): Call<List<Documentos>>

    @POST("Usuarios/Login")
    fun getLogin(@Body loginReq: LoginReq): Call<LoginRes>

}
