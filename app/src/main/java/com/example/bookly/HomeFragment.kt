package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bookly.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {


    private lateinit var drawerLayout: DrawerLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater,
            R.layout.fragment_home,container,false)

        binding.myBooksButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_myBooksFragment)
        }

        binding.reviewsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_myReviewsFragment)
        }

        //binding.root.drawerLayout.openDrawer(GravityCompat.START)
        val tempButton: Button = binding.tempoBtn
        tempButton.setOnClickListener {         (activity as MainActivity?)?.yolo()
        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}