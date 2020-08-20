package com.example.bookly.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.R
import com.example.bookly.backend.Book
import com.example.bookly.backend.BooklyDataHandler


class BooksAdapter(var ct: Context, var bookData: List<Book>) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    private val CHOOSE_BOOK_RECYCLERVIEW: Int = 0
    private val MY_BOOKS_RECYCLERVIEW: Int = 1
    private val TAG = "MyBooksAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        return when (viewType) {
            MY_BOOKS_RECYCLERVIEW -> {
                val inflater = LayoutInflater.from(ct)
                val view = inflater.inflate(R.layout.cardview_item_book, parent, false)
                return BookViewHolder(view)
            }
            CHOOSE_BOOK_RECYCLERVIEW -> {
                val inflater = LayoutInflater.from(ct)
                val view = inflater.inflate(R.layout.cardview_item_book, parent, false)
                return BookViewHolder(view)
            }
            else -> {
                Log.e(TAG, "viewType error in OnCreateViewHolder")
                val inflater = LayoutInflater.from(ct)
                val view = inflater.inflate(R.layout.cardview_item_book, parent, false)
                return BookViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return bookData.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.setBookData(ct, bookData[position])

        //Listeners
        if (getItemViewType(position) == MY_BOOKS_RECYCLERVIEW) {
            //myBooksFragment
            holder.cardView.setOnClickListener { view: View ->
                BooklyDataHandler.getInstance().currentBookFromMyBooks = bookData[position]
                view.findNavController().navigate(R.id.action_myBooksFragment_to_bookFragment)
            }
        } else if (getItemViewType(position) == CHOOSE_BOOK_RECYCLERVIEW) {
            //chooseBookFragment
            holder.cardView.setOnClickListener { view: View ->
                BooklyDataHandler.getInstance().currentBookForReview = bookData[position]
                view.findNavController().navigate(R.id.action_chooseABookFragment_to_writeAReviewFragment)
            }
        }
    }

    /**
     * To check if it is the RecyclerView for myBooksFragment or ChooseBookFragment.
     */
    override fun getItemViewType(position: Int): Int {
        return if (BooklyDataHandler.getInstance().isRecyclerViewOnMyBooks) {
            //fragment_my_books.xml recyclerView
            1
        } else {
            //choose_book.xml recyclerView
            0
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var book_title: TextView = itemView.findViewById(R.id.myBooks_book_title)
        var book_image: ImageView = itemView.findViewById(R.id.myBooks_book_image)
        var cardView: CardView = itemView.findViewById(R.id.myBooks_cardView)

        fun setBookData(ct: Context, book: Book) {
            if (book.coverImage == null) {
                book.coverImage =
                    AppCompatResources.getDrawable(
                        ct,
                        R.drawable.no_cover_image
                    )
            }
            book_image.setImageDrawable(book.coverImage)
            book_title.text = book.title
        }
    }
}