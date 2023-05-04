package com.bhavesh.favproductassignment

import android.app.Application
import com.bhavesh.favproductassignment.di.AppComponent
import com.bhavesh.favproductassignment.di.DaggerAppComponent

class App : Application(){

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().build()
    }

    fun getAppComponent() : AppComponent {
        return appComponent
    }

}