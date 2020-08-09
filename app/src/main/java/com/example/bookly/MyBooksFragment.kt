package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bookly.databinding.FragmentMyBooksBinding

class MyBooksFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMyBooksBinding>(inflater,
            R.layout.fragment_my_books,container,false)

        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myBooksFragment_to_homeFragment)
        }

        binding.reviewsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myBooksFragment_to_myReviewsFragment)
        }

        return binding.root
    }
}