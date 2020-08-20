package com.example.bookly

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bookly.backend.Book
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentBookBinding

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

        return binding.root
    }

    private fun initData() {
        binding.fragmentBookImage.setImageURI(Uri.parse(bookData.coverImage))
        binding.fragmentBookBookTitle.text = bookData.title.toString()
        binding.fragmentBookAuthor.text = bookData.author
        binding.fragmentBookDescription.setText(bookData.description.toString())
    }
}