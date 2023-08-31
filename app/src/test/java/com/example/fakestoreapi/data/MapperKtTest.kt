package com.example.fakestoreapi.data

import com.example.fakestoreapi.data.local.ProductEntity
import com.example.fakestoreapi.data.remote.DetailProduct
import com.example.fakestoreapi.data.remote.Product
import com.example.fakestoreapi.data.remote.Rating
import com.example.fakestoreapi.data.remote.RatingDetail
import org.junit.Assert.*

import org.junit.Test

class MapperKtTest {

    @Test
    fun transformToEntity() {
        //Given
        val productTestUnitario = Product(2,"polera", 345.0,"Polera multicolor","Mejor Producto según mi mamá","http//:www.example.com", Rating(3.9,350))

        //When
        val result = productTestUnitario.transformToEntity()

        //Then
        assertEquals(productTestUnitario.id, result.id)
        assertEquals(productTestUnitario.title, result.title)
        assertEquals(productTestUnitario.price, result.price, 0.0)
        assertEquals(productTestUnitario.description,result.description)
        assertEquals(productTestUnitario.category,result.category)
        assertEquals(productTestUnitario.image,result.image)
        assertEquals(productTestUnitario.rating.rate,result.rate, 0.0)
        assertEquals(productTestUnitario.rating.count,result.count)

    }

    @Test
    fun transformToDetailEntity() {
        val productoDetalleTestUnitario = DetailProduct(3,"chaqueta", 20.5,"negra","Mejor Producto","example.com", RatingDetail(3.0,1))

        //when
        val resultDetalle = productoDetalleTestUnitario.transformToDetailEntity()

        //then
        assertEquals(productoDetalleTestUnitario.id, resultDetalle.id)
        assertEquals(productoDetalleTestUnitario.title, resultDetalle.title)
        assertEquals(productoDetalleTestUnitario.price, resultDetalle.price, 0.0)
        assertEquals(productoDetalleTestUnitario.description,resultDetalle.description)
        assertEquals(productoDetalleTestUnitario.category,resultDetalle.category)
        assertEquals(productoDetalleTestUnitario.image,resultDetalle.image)
        assertEquals(productoDetalleTestUnitario.rating.rate,resultDetalle.rate, 0.0)
        assertEquals(productoDetalleTestUnitario.rating.count,resultDetalle.count)
    }

}