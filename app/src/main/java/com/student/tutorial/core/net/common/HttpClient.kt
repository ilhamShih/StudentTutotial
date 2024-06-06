package com.student.tutorial.core.net.common

import com.student.tutorial.helper.util.UtilTool.getUserAgent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object HttpClient {

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .hostnameVerifier { _, _ -> true }
        .addInterceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().also {
                    it.addHeader("User-Agent", getUserAgent)

                }.build()
            )
        }
        .build()


    private fun getRetrofitClientGson(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Retrofit  - Gson */
    fun apiRetrofitServiceGson(baseUrl: String): HttpClientInterface =
        getRetrofitClientGson(baseUrl).create(HttpClientInterface::class.java)


}