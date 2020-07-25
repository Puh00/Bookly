package com.example.bookly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.bookly.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val navController: NavController = this.findNavController(R.id.myNavHostFragment)
        drawerLayout = binding.drawerLayout

        // Creates a set of top level destinations and saves it in the appBarConfiguration
        val topLevelDestinations = setOf(R.id.homeFragment, R.id.myBooksFragment, R.id.myReviewsFragment)
        appBarConfiguration = AppBarConfiguration.Builder(topLevelDestinations).setDrawerLayout(drawerLayout).build()

        // Setup side panel
        NavigationUI.setupWithNavController(binding.navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }



}