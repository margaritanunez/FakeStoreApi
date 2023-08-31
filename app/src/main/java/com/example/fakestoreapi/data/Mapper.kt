package com.example.fakestoreapi.data

import com.example.fakestoreapi.data.local.DetailProductEntity
import com.example.fakestoreapi.data.local.ProductEntity
import com.example.fakestoreapi.data.remote.DetailProduct
import com.example.fakestoreapi.data.remote.Product

fun Product.transformToEntity(): ProductEntity = ProductEntity(
    this.id,
    this.title,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating.rate,
    this.rating.count)

fun DetailProduct.transformToDetailEntity(): DetailProductEntity = DetailProductEntity(
    this.id,
    this.title,
    this.price,
    this.description,
    this.category,
    this.image,
    this.rating.rate,
    this.rating.count
)