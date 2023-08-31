package com.example.fakestoreapi.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentListBinding

class ListFragment : Fragment() {
    lateinit var binding: FragmentListBinding
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(layoutInflater, container,false)
        initAdapter()
        productViewModel.getAllProductosViewModel()
        return binding.root
    }

    private fun initAdapter() {
        val adapterProducts = AdapterProducts()
        binding.rvList.adapter = adapterProducts
        productViewModel.productLiveData().observe(viewLifecycleOwner){
            adapterProducts.setData(it)
        }
    }
}