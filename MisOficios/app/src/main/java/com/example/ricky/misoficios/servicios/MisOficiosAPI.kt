package com.example.ricky.misoficios.servicios


import com.example.ricky.misoficios.Modelos.LoginReq
import com.example.ricky.misoficios.Modelos.LoginRes
import com.example.ricky.misoficios.Modelos.Oficios
import retrofit2.Call
import retrofit2.http.*

interface MisOficiosAPI {


    @GET("Documentos/{IdUsuarioPropietario}/{inicio}/{final}")
    fun getDocumentos(@Field("IdUsuarioPropietario") IdUsuarioPropietario : String
                      ) : Call<List<Oficios>>

    @POST("Usuarios/Login")
    fun getLogin (@Body loginReq: LoginReq): Call <LoginRes>



}