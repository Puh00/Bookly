package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentMyBooksBinding
import kotlinx.android.synthetic.main.appbar.view.*

class MyBooksFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMyBooksBinding>(
            inflater,
            R.layout.fragment_my_books, container, false
        )

        initBottomNav(binding)
        initAppBar(binding)
        initRecyclerView(binding)

        return binding.root
    }

    private fun initAppBar(binding: FragmentMyBooksBinding) {
        val hamburgerButton = binding.titlebar.hamburgerButton
        hamburgerButton.setOnClickListener { (activity as MainActivity).openDrawer() }
    }

    private fun initBottomNav(binding: FragmentMyBooksBinding) {
        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myBooksFragment_to_homeFragment)
        }

        binding.reviewsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myBooksFragment_to_myReviewsFragment)
        }
    }

    private fun initRecyclerView(binding: FragmentMyBooksBinding) {
        var recyclerView = binding.myBooksRecyclerView
        var myBooksAdapter: MyBooksAdapter =
            MyBooksAdapter(activity!!.applicationContext, BooklyDataHandler.getInstance().books)
        recyclerView.adapter = myBooksAdapter
        recyclerView.layoutManager = GridLayoutManager(activity!!.applicationContext, 3)
    }
}