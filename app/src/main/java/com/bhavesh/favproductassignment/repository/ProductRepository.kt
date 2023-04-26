package com.bhavesh.favproductassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.retrofit.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) {

    private val _product = MutableLiveData<List<Product>>()
    private val _favProductList = MutableLiveData<List<Product>>()

    val product : LiveData<List<Product>>
    get() = _product

    val favProduct : LiveData<List<Product>>
    get() = _favProductList


    suspend fun getProductList() {
        val result = apiService.getProductList()
        if(result.isSuccessful && result.body() != null){
            _product.postValue(result.body()!!.products)
        }
    }


    fun markProductAsFav(pos : Int){
        _product.value?.get(pos)?.isInWishlist = when(_product.value?.get(pos)?.isInWishlist!!){
            false -> true
            true -> false
        }
        _product.postValue(_product.value)
        getFavProductList()
    }

    fun getProduct(pos: Int) : Product? {
        return _product.value?.get(pos)
    }

    private fun getFavProductList(){
        val list = _product.value?.filter {
            it.isInWishlist == true
        }
        _favProductList.postValue(list!!)
    }

}