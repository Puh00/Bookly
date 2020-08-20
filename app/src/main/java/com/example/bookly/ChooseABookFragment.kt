package com.example.bookly

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookly.adapter.BooksAdapter
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.databinding.FragmentChooseABookBinding
import kotlinx.android.synthetic.main.appbar_two.view.*

class ChooseABookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentChooseABookBinding>(
            inflater,
            R.layout.fragment_choose_a_book, container, false
        )

        initAppBar(binding)
        initRecyclerView(binding)

        return binding.root
    }

    private fun initAppBar(binding: FragmentChooseABookBinding){
        val backButton = binding.chooseABookAppBar.backButton
        backButton.setOnClickListener { previousFragment() }
    }

    private fun previousFragment(){
        this.findNavController().popBackStack()
    }

    private fun initRecyclerView(binding: FragmentChooseABookBinding) {
        //To differentiate myBooksFragment and chooseBookFragment
        BooklyDataHandler.getInstance().isRecyclerViewOnMyBooks = false

        val recyclerView = binding.chooseABookRecyclerView
        val booksAdapter: BooksAdapter =
            BooksAdapter(
                activity!!.applicationContext,
                BooklyDataHandler.getInstance().books
            )
        recyclerView.adapter = booksAdapter
        recyclerView.layoutManager = GridLayoutManager(activity!!.applicationContext, 3)
    }
}