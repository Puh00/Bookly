package com.example.bookly

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bookly.databinding.FragmentAddABookBinding


class AddABookFragment : Fragment() {

    lateinit var binding: FragmentAddABookBinding

    val REQUEST_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentAddABookBinding>(
            inflater,
            R.layout.fragment_add_a_book, container, false
        )


        binding.addBookButton.setOnClickListener {
            if (binding.bookTitleTextView.text.toString() != "" && binding.authorTextView.text.toString() != ""){
                addBook()

            }else{

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
            binding.bookCoverImageView.setImageURI(data?.data) // handle chosen image
        }
    }

    private fun addBook() {

    }
}