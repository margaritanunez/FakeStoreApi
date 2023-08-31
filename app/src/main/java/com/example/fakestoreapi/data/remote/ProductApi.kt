package com.example.fakestoreapi.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("products/")
    suspend fun getDataProduct(): Response<List<Product>>

    @GET("products/{id}")
    suspend fun getDataDetailProduct(@Path("id") id: Int): Response<DetailProduct>
}

// los nombres de las clases en general son singulares. hacemos referencia a un producto.