package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookly.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.appbar.view.*

class HomeFragment : Fragment() {

    val s1: List<String> = listOf("PekoIch", "PekoNi", "PekoSan", "Pekonya", "Pekwu")
    val s2: List<String> = listOf("Peko laugh", "Peko Sad", "Peko hip", "Peko nani", "Peko uwu")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home, container, false
        )

        initBottomNav(binding)
        initAppBar(binding)


        //Test recyclerview
        var recyclerView = binding.homePageRecyclerView
        var homePageAdapter: HomePageAdapter = HomePageAdapter(activity!!.applicationContext, s1, s2)

        recyclerView.adapter = homePageAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!.applicationContext)


        return binding.root
    }

    private fun initAppBar(binding: FragmentHomeBinding) {
        val hamburgerButton = binding.titlebar.hamburgerButton
        hamburgerButton.setOnClickListener { (activity as MainActivity).openDrawer() }
    }

    private fun initBottomNav(binding: FragmentHomeBinding) {
        binding.myBooksButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_myBooksFragment)
        }

        binding.reviewsButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_myReviewsFragment)
        }
    }

}