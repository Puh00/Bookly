package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bookly.databinding.FragmentHomeBinding
import com.example.bookly.databinding.FragmentMyBooksBinding

class HomeFragment: Fragment() {


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

        return binding.root
    }
}