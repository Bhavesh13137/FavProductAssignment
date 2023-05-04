package com.bhavesh.favproductassignment.retrofit

import retrofit2.Response

import retrofit2.http.GET
import javax.inject.Inject

interface ApiService{

    @GET("2f06b453-8375-43cf-861a-06e95a951328")
    suspend fun getProductList() : Response<com.bhavesh.favproductassignment.model.Response>


}