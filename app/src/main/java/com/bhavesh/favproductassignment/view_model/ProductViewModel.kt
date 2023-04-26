package com.bhavesh.favproductassignment.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    val productLiveData : LiveData<List<Product>>
    get() = repository.product

    val favProductLiveData : LiveData<List<Product>>
    get() = repository.favProduct

    init {
        viewModelScope.launch {
            repository.getProductList()
        }
    }

    fun markProductAsFav(pos : Int) {
        viewModelScope.launch {
            repository.markProductAsFav(pos)
        }
    }


    fun getProduct(pos : Int) : Product?{
        return repository.getProduct(pos)
    }


}