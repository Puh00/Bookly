package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bookly.databinding.FragmentMyReviewsBinding
import kotlinx.android.synthetic.main.appbar.view.*

class MyReviewsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMyReviewsBinding>(
            inflater,
            R.layout.fragment_my_reviews, container, false
        )

        initBottomNav(binding)
        initAppBar(binding)

        return binding.root
    }

    private fun initAppBar(binding: FragmentMyReviewsBinding) {
        val hamburgerButton = binding.titlebar.hamburgerBtn
        hamburgerButton.setOnClickListener { (activity as MainActivity).openDrawer() }
    }

    private fun initBottomNav(binding: FragmentMyReviewsBinding) {
        binding.myBooksButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myReviewsFragment_to_myBooksFragment)
        }

        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myReviewsFragment_to_homeFragment)
        }
    }
}