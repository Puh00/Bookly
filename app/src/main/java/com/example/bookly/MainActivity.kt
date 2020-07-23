package com.example.bookly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.bookly.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

       /* val homeFragment = HomeFragment()
        val myBooksFragment = MyBooksFragment()
        val myReviewsFragment = MyReviewsFragment()

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.id.bottom_navigation)

*/

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        NavigationUI.setupWithNavController(binding.navView, this.findNavController(R.id.myNavHostFragment))

    }



}