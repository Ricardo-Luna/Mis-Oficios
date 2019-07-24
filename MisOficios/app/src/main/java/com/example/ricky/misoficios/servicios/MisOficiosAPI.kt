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






//http://localhost:50577/api/Documentos/f65c506e-a975-4b96-854a-5bbbf42bb3d9/1/19


}