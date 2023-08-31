package com.example.fakestoreapi.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakestoreapi.data.Repository
import com.example.fakestoreapi.data.local.ProductDataBase
import com.example.fakestoreapi.data.remote.ProductRetrofitClient
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: Repository

    init {
        val productApi = ProductRetrofitClient.getRetrofitProduct()
        val productDataBase = ProductDataBase.getDatabase(application).getProductDao()
        repository = Repository(productApi, productDataBase)
    }

    //Listado productos

    fun productLiveData()= repository.getProductEntity()

    fun getAllProductos()= viewModelScope.launch {
        repository.ChargeAllProducts()
    }
}