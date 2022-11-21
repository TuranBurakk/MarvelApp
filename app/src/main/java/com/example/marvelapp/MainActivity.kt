package com.example.marvelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.marvelapp.utils.gone
import com.example.marvelapp.utils.show
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView =findViewById(R.id.bottomNavigationView)
        navController = Navigation.findNavController(this,R.id.navHostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView,navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

    fun showNavigationBar() {
        bottomNavigationView.show()
    }

    fun hideNavigationBar() {
        bottomNavigationView.gone()
    }
}