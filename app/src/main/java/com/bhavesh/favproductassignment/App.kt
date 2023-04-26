package com.bhavesh.favproductassignment

import android.app.Application
import com.bhavesh.favproductassignment.di.AppComponent
import com.bhavesh.favproductassignment.di.DaggerAppComponent


class App : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

    }

}