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
import com.example.bookly.backend.Book
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentWriteAReviewBinding
import kotlinx.android.synthetic.main.appbar_two.view.*
import java.text.SimpleDateFormat
import java.util.*

class WriteAReviewFragment : Fragment() {

    lateinit var binding: FragmentWriteAReviewBinding
    lateinit var currentBook: Book
    private val currentDate: Date = Date()

    @SuppressLint("SimpleDateFormat")
    private val timeFormat: SimpleDateFormat = SimpleDateFormat("MMM d, YYYY")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWriteAReviewBinding>(
            inflater,
            R.layout.fragment_write_a_review, container, false
        )

        initAppBar()
        initData()
        initListeners()

        return binding.root
    }

    private fun initAppBar() {
        val backButton = binding.writeAReviewTitleBar.backButton
        backButton.setOnClickListener {
            previousFragment()
            Toast.makeText(
                activity,
                "Nopoe", Toast.LENGTH_LONG
            ).show();
        }
    }

    private fun previousFragment() {
        this.findNavController().popBackStack()
    }

    private fun initData() {
        if (BooklyDataHandler.getInstance().currentBookForReview != null) {
            val book = BooklyDataHandler.getInstance().currentBookForReview

            binding.reviewBookTitleTextView.text = book.title
            binding.reviewBookAuthorTextView.text = book.author

            binding.reviewDateTextView.text = timeFormat.format(currentDate).toString()
        } else {
            previousFragment()
            Toast.makeText(activity, "currentBookForReview is null", Toast.LENGTH_LONG).show()
        }
    }

    private fun initListeners() {
        binding.saveReviewButton.setOnClickListener {
            BooklyDataHandler.getInstance().addReview(
                currentBook,
                binding.ratingBar.rating,
                binding.reviewEditText.text.toString(),
                Date()
            )
        }
    }
}