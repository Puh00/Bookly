package com.example.bookly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.nav_header.view.*

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController

    val handler = BooklyDataHandler.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        navController = this.findNavController(R.id.myNavHostFragment)
        drawerLayout = binding.drawerLayout
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        NavigationUI.setupWithNavController(binding.navView, navController)

        handler.setContext(this)
    }

    private fun updateNavHeaderUsername() {
        if(handler.userName != null){
            drawerLayout.nav_header_username.text = BooklyDataHandler.getInstance().userName.toString()
        }else {
            drawerLayout.nav_header_username.text = "Username"
        }
    }

    fun openDrawer() {
        if(handler.profilePicture != null){
            drawerLayout.nav_header_imageView.setImageBitmap(handler.profilePicture)
        }else
        {
            drawerLayout.nav_header_imageView.setImageResource(R.drawable.default_profile_picture)
        }
        updateNavHeaderUsername()
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }


}