package com.example.ricky.misoficios

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.ricky.misoficios.login.GlobalBASE_URL.BASE_URL
import com.example.ricky.misoficios.servicios.MisOficiosAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.disposables.CompositeDisposable
import okhttp3.OkHttpClient

data class RetroCrypto (

    var asunto: String, var remitente: String, var  destinatario: String, var fecha: String, var folio: String
)

private fun initRecyclerView() {





}
public fun loadData() {


}



