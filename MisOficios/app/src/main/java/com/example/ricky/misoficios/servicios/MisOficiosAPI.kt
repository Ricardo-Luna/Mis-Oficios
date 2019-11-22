package com.example.ricky.misoficios.servicios


import com.example.ricky.misoficios.Modelos.*
import retrofit2.Call
import retrofit2.http.*

interface MisOficiosAPI {

    //  @GET("Documentos/{IdUsuarioPropietario}")
    //  fun getDocumentos(
    //      @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    //  ): Call<List<Documentos2>>

    @GET("Oficios/{IdDocumento}")
    fun getDocumentoHtml(
        @Path("IdDocumento") IdDocumento: String
    ): Call<Oficio>

    @GET("GruposUsuarios/{IdGrupo}")
    fun getUsuariosGrupos(
        @Path("IdGrupo") IdGrupo: String
    ): Call<List<Integrantes>>

    @POST("Usuarios/Login")
    fun getLogin(@Body loginReq: LoginReq): Call<LoginRes>

    @POST("MarcarLeido")
    fun setVisto(
        @Body visto: Visto
    ):  Call<Visto>

    @GET("DocumentosCantidad/{IdUsuarioPropietario}")
    fun getCantidadDocs(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ): Call<valor>

    @GET("getUsuarioNombre/{IdUsuario}")
    fun getNickName(
        @Path("IdUsuario") IdUsuario: String
    ): Call<List<nickname>>

    @GET("DocCarpetas/{IdUsuarioPropietario}/{IdCarpeta}")
    fun getDocsCarpetas(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String,
        @Path("IdCarpeta") IdCarpeta: String
    ): Call<List<Documentos>>

    @GET("Carpetas/{IdUsuarioPropietario}")
    fun getCarpetas(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ): Call<List<folder>>

    @GET("Grupos/{IdUsuarioPropietario}")
    fun getGrupos(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ): Call<List<Grupos>>

    @GET("Documentos/Destinatarios/{IdDocumento}")
    fun getRemitentes(
        @Path("IdDocumento") IdDocumento: String
    ): Call<List<Remitentes>>

    @GET("CarpetaRecibidos/{IdUsuarioPropietario}")
    fun getCarpetaRecibidos(
        @Path("IdUsuarioPropietario") IdUsuarioPropietario: String
    ): Call<List<Carpetas>>

    @GET("Documentos/Destinatario/{IdUsuarioDestinatario}/{IdDocumento}")
    fun getDestinatariosLeido(
        @Path("IdUsuarioDestinatario") IdUsuarioDestinatario: String,
        @Path("IdDocumento") IdDocumento: String
    ): Call<List<Remitentes>>
}

