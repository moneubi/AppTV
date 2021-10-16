package com.sunu.app.apptv.di

import com.sunu.app.apptv.common.Constatnts
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        //Method to give the instance of retrofit
        fun getRetrofitInstance(): Retrofit{

            //add my interceptor from logging
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)
            //return the instance of retrofit
            return Retrofit.Builder()
                .baseUrl(Constatnts.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}