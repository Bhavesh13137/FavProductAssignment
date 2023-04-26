package com.bhavesh.favproductassignment.di

import com.bhavesh.favproductassignment.retrofit.ApiService
import com.bhavesh.favproductassignment.utils.Constant
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetrofitHelper {

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}