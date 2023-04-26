package com.bhavesh.favproductassignment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bhavesh.favproductassignment.MainActivity
import com.bhavesh.favproductassignment.R
import com.bhavesh.favproductassignment.adapter.ProductAdapter
import com.bhavesh.favproductassignment.databinding.FragmentProductListBinding
import com.bhavesh.favproductassignment.model.Product

class ProductListFragment : Fragment() , ProductAdapter.OnClickListener{
    private lateinit var binding : FragmentProductListBinding
    private val adapter : ProductAdapter = ProductAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }
    private fun initView() {
        val layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        (activity as MainActivity).showNewProgress()
        (activity as MainActivity).getViewModel().productLiveData.observe(viewLifecycleOwner) {
            adapter.setList(it)
            (activity as MainActivity).hideNewProgress()
        }
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).title = "Product List"
        (activity as MainActivity).setBackButton(false)
    }

    override fun onViewDetails(i: Int, model: Product) {
        val bundle = Bundle()
        bundle.putInt("index",i)
        findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment,bundle)
    }

    override fun onFav(i: Int, model: Product) {
        (activity as MainActivity).getViewModel().markProductAsFav(i)
    }

    override fun onAddToCart(i: Int, model: Product) {

    }
}