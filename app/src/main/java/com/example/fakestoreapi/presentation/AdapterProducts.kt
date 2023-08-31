package com.example.fakestoreapi.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fakestoreapi.data.local.ProductEntity
import com.example.fakestoreapi.databinding.ItemListBinding

class AdapterProducts: RecyclerView.Adapter<AdapterProducts.ItemListViewHolder>() {

    lateinit var binding: ItemListBinding
    private val listProducts= mutableListOf<ProductEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val product= listProducts[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return listProducts.size
    }

    fun setData(products: List<ProductEntity>){
        this.listProducts.clear()
        this.listProducts.addAll(products)
        notifyDataSetChanged()
    }

    class ItemListViewHolder (val productBinding: ItemListBinding): RecyclerView.ViewHolder(productBinding.root){

        fun bind(product: ProductEntity){
            val bundle = Bundle()
            productBinding.tvName.text = product.title
            productBinding.imgProduct.load(product.image)

        }

    }

}