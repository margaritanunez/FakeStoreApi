package com.example.fakestoreapi.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    //Insertar lista de productos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(productList: List<ProductEntity>)

    @Query("select * from product_table order by id ASC")
    fun getAllProducts(): LiveData<List<ProductEntity>>

    // Insertar detalle de un producto
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailSelectedProduct(detailProductEntity: DetailProductEntity)

    @Query("select* from detail_product_table where id=:id")
    fun getDetailSelectedProduct(id: Int): LiveData<DetailProductEntity>
}