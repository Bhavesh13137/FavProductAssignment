package com.bhavesh.favproductassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bhavesh.favproductassignment.view_model.ProductViewModel
import com.bhavesh.favproductassignment.view_model.ProductViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var progressBar : ProgressBar
    private lateinit var bottomNavigationView : BottomNavigationView
    @Inject
    lateinit var productViewModelFactory: ProductViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,productViewModelFactory)[ProductViewModel::class.java]
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStart() {
        super.onStart()
        //val navController = findNavController(R.id.fragment_container_view)
        //bottomNavigationView.setupWithNavController(navController)

    }

    private fun init(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

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
    fun getViewModel() : ProductViewModel = viewModel

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