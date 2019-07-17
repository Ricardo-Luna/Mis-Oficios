package com.example.ricky.misoficios.servicios

import com.example.ricky.misoficios.Modelos.LoginReq
import com.example.ricky.misoficios.Modelos.LoginRes
import com.example.ricky.misoficios.Modelos.Oficios
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MisOficiosAPI {


    @GET("Documentos/{IdUsuarioPropietario}/1/19")
    fun getDocumentos(@Path("IdUsuarioPropietario") IdUsuarioPropietario : String) : Call<List<Oficios>>






//http://localhost:50577/api/Documentos/f65c506e-a975-4b96-854a-5bbbf42bb3d9/1/19


}