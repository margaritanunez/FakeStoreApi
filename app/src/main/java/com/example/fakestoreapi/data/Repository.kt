package com.example.fakestoreapi.data

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.fakestoreapi.data.local.DetailProductEntity
import com.example.fakestoreapi.data.local.ProductDao
import com.example.fakestoreapi.data.local.ProductEntity
import com.example.fakestoreapi.data.remote.DetailProduct
import com.example.fakestoreapi.data.remote.Product
import com.example.fakestoreapi.data.remote.ProductApi

class Repository(private val productApi: ProductApi, private val productDao: ProductDao)  {

    //Listado de todos los productos
    fun getProductEntity(): LiveData<List<ProductEntity>> = productDao.getAllProducts()

    suspend fun chargeAllProducts() {
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

    // Detalle de producto de acuerdo al ID seleccionado

    fun getDetailProductEntity(id: Int): LiveData<DetailProductEntity> = productDao.getDetailSelectedProduct(id)
    suspend fun chargeDetailProduct(id: Int){
        try {
            val responseDetail = productApi.getDataDetailProduct(id)
            if (responseDetail.isSuccessful) {
                val respDetail = responseDetail.body()
                respDetail?.let {
                    val detailsProductEntity = it.transformToDetailEntity()
                    productDao.insertDetailSelectedProduct(detailsProductEntity)
                }
            }else {
                Log.e("repository", responseDetail.errorBody().toString())
            }
        } catch (exception: Exception) {
            Log.e("catch", "")
        }
    }
}

