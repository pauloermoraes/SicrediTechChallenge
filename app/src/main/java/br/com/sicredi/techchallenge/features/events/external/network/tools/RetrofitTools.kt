package br.com.sicredi.techchallenge.features.events.external.network.tools

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass

object RetrofitTools {
    fun <T : Any> getRetrofit(url: String, serviceKClass: KClass<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(
                OkHttpClient.Builder().apply {
                    connectTimeout(30, TimeUnit.SECONDS)
                    readTimeout(30, TimeUnit.SECONDS)
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(serviceKClass.java)
    }
}