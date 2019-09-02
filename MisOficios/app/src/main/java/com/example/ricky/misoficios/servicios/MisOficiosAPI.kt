package com.example.ricky.misoficios.servicios


import com.example.ricky.misoficios.Modelos.*
import retrofit2.Call
import retrofit2.http.*

interface MisOficiosAPI {


    @GET("Documentos/{IdUsuarioPropietario}")
    fun getDocumentos( @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ): Call<List<Documentos2>>

    @POST("Usuarios/Login")
    fun getLogin(@Body loginReq: LoginReq): Call<LoginRes>

    @GET("DocumentosCantidad/{IdUsuarioPropietario}")
    fun getCantidadDocs(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ): Call<valor>


    @GET("/getUsuarioNombre/{IdUsuario}")
    fun getNickName(
        @Path("IdUsuario") IdUsuario: String
    ):Call<nickname>


    @GET("api/Carpetas/{IdUsuarioPropietario}")
    fun getCarpetas(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ):Call<List<folder>>



    @GET("api/DocCarpetas/{IdUsuarioPropietario}/{IdCarpeta}")
    fun getDocsCarpetas(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String,
        @Path("IdCarpeta") idCarpetas: String
    ): Call<List<Documentos2>>

}
