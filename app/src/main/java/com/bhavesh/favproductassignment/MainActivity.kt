package com.bhavesh.favproductassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar : ProgressBar
    private lateinit var bottomNavigationView : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        progressBar = findViewById(R.id.progressBar)
        bottomNavigationView = findViewById(R.id.bottom_nav)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
    }



    fun setBackButton(b : Boolean){
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(b)
            supportActionBar!!.setDisplayShowHomeEnabled(b)
        }
        if(b){
            bottomNavigationView.visibility = View.GONE
        }else{
            bottomNavigationView.visibility = View.VISIBLE
        }
    }

    fun showNewProgress() {
        if(this::progressBar.isInitialized) {
            progressBar.isIndeterminate = true
            progressBar.visibility = View.VISIBLE
        }
    }

    fun hideNewProgress() {
        if(this::progressBar.isInitialized) {
            progressBar.isIndeterminate = false
            progressBar.visibility = View.GONE
        }
    }
}