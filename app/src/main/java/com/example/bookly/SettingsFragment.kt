package com.example.bookly

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.appbar_two.view.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(
            inflater,
            R.layout.fragment_settings, container, false
        )

        initAppBar(binding)
        initListeners(binding)

        return binding.root
    }

    private fun initAppBar(binding: FragmentSettingsBinding) {
        binding.settingsAppBar.currentFragment.text = "Settings"

        val backButton = binding.settingsAppBar.backButton
        backButton.setOnClickListener { previousFragment() }
    }

    private fun previousFragment() {
        this.findNavController().popBackStack()
    }

    @SuppressLint("ShowToast")
    private fun initListeners(binding: FragmentSettingsBinding){
        binding.settingsResetButton.setOnClickListener {
            BooklyDataHandler.getInstance().resetData()
            Toast.makeText(activity, "DATA HAS BEEN RESET", Toast.LENGTH_LONG).show()
        }
    }
}