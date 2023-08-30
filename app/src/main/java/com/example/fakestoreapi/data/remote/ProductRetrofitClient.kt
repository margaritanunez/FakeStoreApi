package com.example.fakestoreapi.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRetrofitClient {
    companion object{
        private const val BASE_URL = "https://fakestoreapi.com/"

        fun getRetrofitProduct() : ProductApi{
            val mRetrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return mRetrofit.create(ProductApi::class.java)
        }
    }
}