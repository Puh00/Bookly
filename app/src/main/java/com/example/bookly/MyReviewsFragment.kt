package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookly.adapter.ReviewsAdapter
import com.example.bookly.backend.BooklyDataHandler
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
        initFloatingActionBar(binding)
        initRecyclerView(binding)

        return binding.root
    }

    private fun initAppBar(binding: FragmentMyReviewsBinding) {
        val hamburgerButton = binding.titlebar.hamburgerButton
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

    private fun initFloatingActionBar(binding: FragmentMyReviewsBinding) {
        binding.myReviewsFloatingActionButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myReviewsFragment_to_chooseABookFragment)
        }
    }

    private fun initRecyclerView(binding: FragmentMyReviewsBinding) {
        val recyclerView = binding.myReviewsRecyclerView
        val reviewsAdapter: ReviewsAdapter =
            ReviewsAdapter(activity!!.applicationContext, BooklyDataHandler.getInstance().reviews)
        recyclerView.adapter = reviewsAdapter
        recyclerView.layoutManager = GridLayoutManager(activity!!.applicationContext, 1)
    }
}