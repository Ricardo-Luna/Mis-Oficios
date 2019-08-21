package com.example.ricky.misoficios.servicios

import com.example.ricky.misoficios.login
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    //Creando el objeto de retrofit_________________________________________________

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())

            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: MisOficiosAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(login.GlobalBASE_URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(MisOficiosAPI::class.java)
    }

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.0.17/ApiMovilMisOficios/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}