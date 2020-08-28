package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookly.backend.Book
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentBookBinding
import kotlinx.android.synthetic.main.appbar_two.view.*

class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding
    lateinit var bookData: Book

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBookBinding>(
            inflater,
            R.layout.fragment_book, container, false
        )
        bookData = BooklyDataHandler.getInstance().currentBookFromMyBooks

        initData()
        initAppBar()

        return binding.root
    }

    private fun initData() {
        if (bookData.coverImage != null) {
            binding.fragmentBookImage.setImageBitmap(bookData.coverImage)
        } else {
            binding.fragmentBookImage.setImageResource(R.drawable.no_cover_image)
        }
        binding.fragmentBookBookTitle.text = bookData.title.toString()
        binding.fragmentBookAuthor.text = bookData.author
        binding.fragmentBookDescription.setText(bookData.description.toString())
    }

    private fun initAppBar(){
        binding.fragmentBookAppBar.backButton.setOnClickListener{ previousFragment() }
        binding.fragmentBookAppBar.currentFragment.text = bookData.title.toString()
    }

    private fun previousFragment(){
        this.findNavController().popBackStack()
    }
}