package com.example.bookly

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.backend.Book

class MyBooksAdapter(var ct: Context, var bookData: List<Book>) :
    RecyclerView.Adapter<MyBooksAdapter.BookViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        var inflater = LayoutInflater.from(ct)
        var view = inflater.inflate(R.layout.cardview_item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookData.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        if(bookData[position].coverImage == null){
            bookData[position].coverImage = AppCompatResources.getDrawable(ct, R.drawable.no_cover_image)
        }
        holder.book_image.setImageDrawable(bookData[position].coverImage)
        holder.book_title.text = bookData[position].title
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var book_title: TextView = itemView.findViewById(R.id.myBooks_book_title)
        var book_image: ImageView = itemView.findViewById(R.id.myBooks_book_image)
    }
}