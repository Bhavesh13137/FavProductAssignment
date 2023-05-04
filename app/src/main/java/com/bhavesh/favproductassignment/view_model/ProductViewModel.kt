package com.bhavesh.favproductassignment.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.repository.ProductRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    fun markProductAsFav(model: Product) {
        viewModelScope.launch {
            repository.markProductAsFav(model)
        }
    }

    fun markProductAsFav(pos : Int, model: Product) {
        viewModelScope.launch {
            repository.markProductAsFav(pos,model)
        }
    }

    fun markProductDelete(pos : Int, model: Product) {
        viewModelScope.launch {
            repository.markProductDelete(pos,model)
        }
    }


    fun getProduct(pos : Int) : Product?{
        return repository.getProduct(pos)
    }

    fun getFavProduct(){
        viewModelScope.launch {
            repository.getFavProduct()
        }
    }


}