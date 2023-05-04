package com.bhavesh.favproductassignment.di

import com.bhavesh.favproductassignment.ui.FavProductListFragment
import com.bhavesh.favproductassignment.ui.ProductDetailsFragment
import com.bhavesh.favproductassignment.ui.ProductListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RetrofitHelper::class])
interface AppComponent {
    fun inject(favProductListFragment : FavProductListFragment)
    fun inject(productDetailsFragment : ProductDetailsFragment)
    fun inject(productListFragment : ProductListFragment)
}