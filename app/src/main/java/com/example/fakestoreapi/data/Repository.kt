package com.example.fakestoreapi.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.fakestoreapi.data.local.ProductDao
import com.example.fakestoreapi.data.local.ProductEntity
import com.example.fakestoreapi.data.remote.Product
import com.example.fakestoreapi.data.remote.ProductApi

class Repository(private val productApi: ProductApi, private val productDao: ProductDao)  {


    fun getProductEntity(): LiveData<List<ProductEntity>> = productDao.getAllProducts()

    suspend fun ChargeAllProducts() {
        try {
            val response = productApi.getDataProduct()
            if (response.isSuccessful) {
                val resp = response.body()
                resp?.let {
                    val productosEntity = it.map { it.transformToEntity() }
                    productDao.insertAllProducts(productosEntity)
                }
            }else {
                Log.e("repository", response.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("catch", "")

        }
    }
    private fun Product.transformToEntity(): ProductEntity = ProductEntity(
        this.id,
        this.title,
        this.price,
        this.description,
        this.category,
        this.image,
        this.rating.rate,
        this.rating.count
    )
}

