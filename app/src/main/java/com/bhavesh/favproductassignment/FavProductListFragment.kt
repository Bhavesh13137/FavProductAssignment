package com.bhavesh.favproductassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhavesh.favproductassignment.databinding.FragmentFavProductListBinding


class FavProductListFragment : Fragment() {

    private lateinit var binding : FragmentFavProductListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavProductListBinding.inflate(inflater, container, false)

        return binding.root
    }


}