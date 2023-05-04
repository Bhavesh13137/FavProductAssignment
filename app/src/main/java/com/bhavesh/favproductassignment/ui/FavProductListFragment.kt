package com.bhavesh.favproductassignment.ui

import android.os.Bundle
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
import com.bhavesh.favproductassignment.adapter.FavProductAdapter
import com.bhavesh.favproductassignment.databinding.FragmentFavProductListBinding
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.view_model.ProductViewModel
import com.bhavesh.favproductassignment.view_model.ProductViewModelFactory
import javax.inject.Inject

class FavProductListFragment : Fragment() , FavProductAdapter.OnClickListener{

    private lateinit var binding : FragmentFavProductListBinding
    private val adapter : FavProductAdapter = FavProductAdapter(this)
    @Inject
    lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var viewModel : ProductViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFavProductListBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun initView() {
        val layoutManager = GridLayoutManager(activity, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[ProductViewModel::class.java]
        viewModel.favProductLiveData.observe(viewLifecycleOwner){ list ->
            adapter.setList(list)
        }
        viewModel.getFavProduct()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).getAppComponent().inject(this)
        initView()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).title = "Favourite Product List"
        //(activity as MainActivity).setBackButton(false)
//        (activity as MainActivity).getViewModel().favProductLiveData.observe(viewLifecycleOwner){
//            adapter.setList(it)
//        }
    }

    override fun onViewDetails(i: Int, model: Product) {
        val bundle = Bundle()
        bundle.putInt("index",i)
        bundle.putInt("id",model.id!!.toInt())
        findNavController().navigate(R.id.action_favProductListFragment_to_productDetailsFragment,bundle)
    }

    override fun onDelete(i: Int, model: Product) {
        viewModel.markProductDelete(i,model)
    }


}