package com.example.fakestoreapi.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("products/")
    suspend fun getDataProduct(): Response<List<Product>>
}

// los nombres de las clases en general son singulares. hacemos referencia a un producto.