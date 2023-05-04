package com.bhavesh.favproductassignment.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bhavesh.favproductassignment.App
import com.bhavesh.favproductassignment.MainActivity
import com.bhavesh.favproductassignment.R
import com.bhavesh.favproductassignment.adapter.ProductAdapter
import com.bhavesh.favproductassignment.databinding.FragmentProductListBinding
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.view_model.ProductViewModel
import com.bhavesh.favproductassignment.view_model.ProductViewModelFactory
import javax.inject.Inject

class ProductListFragment : Fragment() , ProductAdapter.OnClickListener{
    private lateinit var binding : FragmentProductListBinding
    private val adapter : ProductAdapter = ProductAdapter(this)
    @Inject
    lateinit var viewModelFactory: ProductViewModelFactory
    lateinit var viewModel : ProductViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.e("ProductListFragment","444")
        binding = FragmentProductListBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("ProductListFragment","111")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ProductListFragment","222")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).getAppComponent().inject(this)

        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[ProductViewModel::class.java]
        initView()

    }


    private fun initView() {
        val layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        (activity as MainActivity).showNewProgress()
        //viewModel = ViewModelProvider(requireActivity(),productViewModelFactory)[ProductViewModel::class.java]
        viewModel.productLiveData.observe(viewLifecycleOwner) {
            adapter.setList(it)
            (activity as MainActivity).hideNewProgress()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e("ProductListFragment","666")
        (activity as MainActivity).title = "Product List"
        (activity as MainActivity).setBackButton(false)
    }

    override fun onViewDetails(i: Int, model: Product) {
        val bundle = Bundle()
        bundle.putInt("index",i)
        bundle.putInt("id",model.id!!.toInt())
        findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment,bundle)
    }

    override fun onFav(i: Int, model: Product) {
        viewModel.markProductAsFav(i,model)
    }

    override fun onAddToCart(i: Int, model: Product) {

    }
}