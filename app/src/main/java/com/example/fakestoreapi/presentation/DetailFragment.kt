package com.example.fakestoreapi.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.fakestoreapi.R
import com.example.fakestoreapi.databinding.FragmentDetailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"

class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var id: Int = 0
    private lateinit var binding: FragmentDetailBinding
    private val productViewModel: ProductViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(layoutInflater, container, false)
        productViewModel.getDetailsProductViewModel(id)
        initListener()
        return binding.root
    }

    private fun initListener() {
        productViewModel.detailProductLiveData(id).observe(viewLifecycleOwner){
            if(it != null){
                binding.imgDetailProduct.load(it.image)
                binding.tvDetailDescription.text = it.description
                binding.tvDetailPrice.text= it.price.toString()
                binding.tvDetailname.text = it.title

            }
        }
    }

    }

