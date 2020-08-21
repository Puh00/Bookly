package com.example.bookly

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.bookly.backend.Book
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.backend.Review
import com.example.bookly.databinding.FragmentWriteAReviewBinding
import kotlinx.android.synthetic.main.appbar_two.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class WriteAReviewFragment : Fragment() {

    lateinit var binding: FragmentWriteAReviewBinding
    lateinit var currentBook: Book
    private val currentDate: Date = Date()
    var reviewExists by Delegates.notNull<Boolean>()
    lateinit var review: Review

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

        reviewExists = doesReviewExist()
        initAppBar()
        initData()
        initListeners()

        return binding.root
    }

    private fun initAppBar() {
        val backButton = binding.writeAReviewTitleBar.backButton
        backButton.setOnClickListener { previousFragment() }
        binding.writeAReviewTitleBar.currentFragment.text = "Write a review"
    }

    private fun previousFragment() {
        this.findNavController().popBackStack()
    }

    private fun backToMyReviews(view: View?) {
        view!!.findNavController().navigate(R.id.action_writeAReviewFragment_to_myReviewsFragment)

    }

    private fun initData() {
        if (BooklyDataHandler.getInstance().currentBookForReview != null) {
            val book = BooklyDataHandler.getInstance().currentBookForReview

            if (book.coverImage != null) {
                binding.bookCoverReview.setImageBitmap(book.coverImage)
            } else {
                binding.bookCoverReview.setImageResource(R.drawable.no_cover_image)
            }
            binding.reviewBookTitleTextView.text = book.title
            binding.reviewBookAuthorTextView.text = book.author

            binding.reviewDateTextView.text = timeFormat.format(currentDate).toString()
        } else {
            previousFragment()
            Toast.makeText(activity, "currentBookForReview is null", Toast.LENGTH_LONG).show()
        }

        //If review exists
        if (reviewExists) {
            binding.ratingBar.rating = review.rating
            binding.reviewEditText.setText(review.comment)
        }
    }

    private fun initListeners() {
        binding.saveReviewButton.setOnClickListener {

            if (!reviewExists) {
                BooklyDataHandler.getInstance().addReview(
                    currentBook,
                    binding.ratingBar.rating,
                    binding.reviewEditText.text.toString(),
                    Date()
                )
            } else {
                //Edits review
                BooklyDataHandler.getInstance().editReview(
                    review,
                    currentBook,
                    binding.ratingBar.rating,
                    binding.reviewEditText.text.toString(),
                    Date()
                )
            }
            backToMyReviews(view)
        }
    }

    private fun doesReviewExist(): Boolean {
        currentBook = BooklyDataHandler.getInstance().currentBookForReview

        for (r: Review in BooklyDataHandler.getInstance().reviews) {
            if (currentBook == r.book) {
                review = r
                return true
            }
        }
        return false
    }


}