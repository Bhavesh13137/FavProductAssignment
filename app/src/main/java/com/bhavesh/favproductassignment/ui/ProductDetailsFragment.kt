package com.bhavesh.favproductassignment.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bhavesh.favproductassignment.App
import com.bhavesh.favproductassignment.MainActivity
import com.bhavesh.favproductassignment.R
import com.bhavesh.favproductassignment.databinding.FragmentProductDetailsBinding
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.view_model.ProductViewModel
import com.bhavesh.favproductassignment.view_model.ProductViewModelFactory
import com.bumptech.glide.Glide
import javax.inject.Inject

class ProductDetailsFragment : Fragment() {
    private lateinit var binding : FragmentProductDetailsBinding
    private var product : Product? = null
    @Inject
    lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var viewModel : ProductViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        setupMenu()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).title = "Product Detail"
        (activity as MainActivity).setBackButton(true)
    }

    override fun onStop() {
        super.onStop()
        (activity as MainActivity).setBackButton(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity().application as App).getAppComponent().inject(this)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory)[ProductViewModel::class.java]
        product = viewModel.getProduct(arguments?.getInt("id",0)!!)
        setLike()
        Glide.with(binding.imageView.context).asBitmap().load(product?.imageURL).into(binding.imageView)
        binding.txtProductName.text = product?.title
        binding.txtMrp.text = binding.txtMrp.context.getString(R.string.rupees).plus(product?.saleUnitPrice)
        if(product?.ratingCount == 0.0){
            binding.txtProductRatingBarProduct.rating = 3.7.toFloat()
            binding.txtProductRatingUserCount.text = product?.ratingCount.toString().plus(" [").plus(product?.totalReviewCount).plus(" Users]")
        }else {
            binding.txtProductRatingBarProduct.rating = product?.ratingCount?.toFloat() ?: 0F
            binding.txtProductRatingUserCount.text = product?.ratingCount.toString().plus(" [").plus(product?.totalReviewCount).plus(" Users]")
        }
        if(product?.isInWishlist!!){
            binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
        binding.btnAddToCart.text = product?.addToCartButtonText
        binding.btnLike.setOnClickListener {
            product = viewModel.getProduct(arguments?.getInt("id",0)!!)
            viewModel.markProductAsFav(product!!)
            setLike()
        }
    }
   private fun setLike(){
        if(product?.isInWishlist!!){
            binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }


    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onPrepareMenu(menu: Menu) {
                // Handle for example visibility of menu items
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                //menuInflater.inflate(R.menu.menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Validate and handle the selected menu item
                if(menuItem.itemId == android.R.id.home){
                    findNavController().navigateUp()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}