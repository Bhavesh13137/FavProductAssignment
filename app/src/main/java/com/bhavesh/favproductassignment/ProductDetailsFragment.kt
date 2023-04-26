package com.bhavesh.favproductassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhavesh.favproductassignment.databinding.FragmentProductDetailsBinding


class ProductDetailsFragment : Fragment() {
    private lateinit var binding : FragmentProductDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }
}