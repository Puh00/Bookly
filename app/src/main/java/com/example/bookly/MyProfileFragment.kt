package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bookly.databinding.FragmentMyProfileBinding

class MyProfileFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMyProfileBinding>(inflater,
            R.layout.fragment_my_profile,container,false)

        binding.myBooksButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myProfileFragment_to_myBooksFragment)
        }

        binding.homeButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myProfileFragment_to_homeFragment)
        }

        return binding.root
    }
}