package com.example.bookly

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentAddABookBinding
import kotlinx.android.synthetic.main.appbar_two.view.*


class AddABookFragment : Fragment() {

    lateinit var binding: FragmentAddABookBinding

    private val REQUEST_CODE = 100

     private lateinit var imageUri : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddABookBinding>(
            inflater,
            R.layout.fragment_add_a_book, container, false
        )

        initAppBar()

        binding.addBookButton.setOnClickListener {
            if (binding.bookTitleTextView.text.toString() != "" && binding.authorTextView.text.toString() != ""){
                addBook()
                this.findNavController().popBackStack()
            }else{
                Toast.makeText(getActivity(),
                    "You dumb fuck", Toast.LENGTH_LONG).show();
            }

        }

        binding.bookCoverImageView.setOnClickListener{
            openGalleryForImage()
        }

        return binding.root
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            imageUri = (data?.data as Uri).toString()
            binding.bookCoverImageView.setImageURI(data?.data) // handle chosen image
        }
    }

    private fun initAppBar() {
        val backButton = binding.addABookAppbar.backButton
        backButton.setOnClickListener { previousFragment() }
        binding.addABookAppbar.currentFragment.text = "Add a Book"
    }

    private fun previousFragment(){
        this.findNavController().popBackStack()
    }

    private fun addBook() {
        BooklyDataHandler.getInstance().addBook(binding.bookTitleTextView.text.toString(), binding.authorTextView.text.toString(),
        binding.descriptionTextView.text.toString(),
            if (binding.numberOfPagesNumberView.text.toString() != "") binding.numberOfPagesNumberView.text.toString()
                .toShort() else 0, imageUri)
    }
}