package com.example.bookly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentMyProfileBinding
import kotlinx.android.synthetic.main.appbar_two.view.*
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil


class MyProfileFragment : Fragment() {

    private val REQUEST_CODE = 100
    private lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentMyProfileBinding>(
            inflater,
            R.layout.fragment_my_profile, container, false
        )

        initAppBar()
        initData()
        initListeners()

        return binding.root
    }

    private fun initData() {
        binding.usernameTextView.text = BooklyDataHandler.getInstance().userName
        binding.numberOfBooksTextView.text =
            BooklyDataHandler.getInstance().numberOfBooks().toString()
        if (BooklyDataHandler.getInstance().profilePicture == null) {
            binding.profilePictureImageView.setImageResource(R.drawable.default_profile_picture)
        } else {
            binding.profilePictureImageView.setImageDrawable(BooklyDataHandler.getInstance().profilePicture)
        }
    }

    private fun initAppBar() {
        val backButton = binding.myProfileAppBar.backButton
        backButton.setOnClickListener { (activity as MainActivity).openDrawer() }
        binding.myProfileAppBar.currentFragment.text = "My Profile"
    }

    private fun initListeners() {
        binding.usernameTextView.setOnClickListener {
            binding.usernameEditText.visibility = View.VISIBLE
            binding.usernameTextView.visibility = View.INVISIBLE
            binding.usernameEditText.requestFocus()

            UIUtil.showKeyboard(context, binding.usernameEditText)

        }

        binding.usernameEditText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.usernameEditText.visibility = View.INVISIBLE
                binding.usernameTextView.visibility = View.VISIBLE
                UIUtil.hideKeyboard(activity as MainActivity)

                BooklyDataHandler.getInstance().userName = binding.usernameEditText.text.toString()
                binding.usernameTextView.text = BooklyDataHandler.getInstance().userName
            }
        }

        binding.profilePictureImageView.setOnClickListener { openGalleryForImage() }
    }


    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            binding.profilePictureImageView.setImageURI(data?.data) // handle chosen image
            BooklyDataHandler.getInstance().profilePicture = binding.profilePictureImageView.drawable
        }
    }


}