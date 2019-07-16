package com.example.ricky.misoficios

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
//import com.example.ricky.misoficios.login.GlobalBASE_URL.BASE_URL
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.disposables.CompositeDisposable

data class RetroCrypto (

    var asunto: String, var remitente: String, var  destinatario: String, var fecha: String, var folio: String
)

private fun initRecyclerView() {

//Use a layout manager to position your items to look like a standard ListView//



}
//public fun loadData(){
//    //Creando el objeto de retrofit_________________________________________________
//
//    val requestInterface = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//
//        //Hacer usable el objeto de Retrofit invocando .build()_____________________
//
//        .build().create(MisOficiosAPI::class.java)





