package com.bhavesh.favproductassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bhavesh.favproductassignment.model.Product
import com.bhavesh.favproductassignment.retrofit.ApiService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val apiService: ApiService) {

    private val _product = MutableLiveData<List<Product>>()
    private val _favProduct = MutableLiveData<List<Product>>()

    val product : LiveData<List<Product>>
    get() = _product

    val favProduct : LiveData<List<Product>>
    get() = _favProduct


    suspend fun getProductList() {
        val result = apiService.getProductList()
        if(result.isSuccessful && result.body() != null){
            _product.postValue(result.body()!!.products)
        }
    }

    fun markProductAsFav(product : Product){
        var k = 0
        for((j, i) in _product.value!!.withIndex()){
            if(product.id.equals(i.id)){
                k = j
                break
            }
        }

        _product.value?.get(k)?.isInWishlist = when(product.isInWishlist!!){
            false -> true
            true -> false
        }
        _product.postValue(_product.value)
    }

    fun markProductAsFav(pos : Int, product : Product){
        _product.value?.get(pos)?.isInWishlist = when(product.isInWishlist!!){
            false -> true
            true -> false
        }
        _product.postValue(_product.value)
    }




     fun markProductDelete(pos : Int, product : Product){
         _favProduct.value?.get(pos)?.isInWishlist = when(product.isInWishlist!!){
            false -> true
            true -> false
        }

         val list = _favProduct.value?.filter {
             it.isInWishlist == true
         }

         _favProduct.postValue(list!!)
        //getFavProduct()
    }


    fun getProduct(pos: Int) : Product? {
        for(i in _product.value!!){
            if(i.id.equals(pos.toString())){
                return i
            }
        }
        return null
    }

    fun getFavProduct(){
        val list = _product.value?.filter {
            it.isInWishlist == true
        }
        _favProduct.postValue(list!!)
    }


}