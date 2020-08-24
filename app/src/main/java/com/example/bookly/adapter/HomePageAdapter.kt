package com.example.bookly.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.R
import com.example.bookly.backend.FeedAction
import com.example.bookly.backend.FeedItem
import java.text.SimpleDateFormat

class HomePageAdapter(var ct: Context, var feedData: List<FeedItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val timeFormat: SimpleDateFormat = SimpleDateFormat("MMM d, YYYY")
    private val BOOK_ITEM: Int = 0
    private val REVIEW_ITEM: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(ct)
        return return when (viewType){
            BOOK_ITEM -> {
                val view = inflater.inflate(R.layout.feed_item_book, parent, false)
                BookViewHolder(view)
            }
            REVIEW_ITEM ->{
                val view = inflater.inflate(R.layout.item_cardview_review, parent, false)
                ReviewViewHolder(view)
            }
            else -> {
                throw IllegalArgumentException("HOMEPAGEADAPTER ILLEGAL ARGUMENT")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == BOOK_ITEM){
            (holder as BookViewHolder).setBookData(ct, feedData[position], timeFormat)
        }else if(getItemViewType(position) == REVIEW_ITEM){
            (holder as ReviewViewHolder).setReviewData(ct, feedData[position], timeFormat)
        }
    }

    override fun getItemCount(): Int {
        return feedData.size
    }

    override fun getItemViewType(position: Int): Int {

        return if(feedData[position].feedAction == FeedAction.BOOK_ADDED || feedData[position].feedAction == FeedAction.BOOK_EDITED){
            //Book item
            0
        }else{
            //Review item
            1
        }
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var book_title: TextView = itemView.findViewById(R.id.feed_item_book_title)
        var book_image: ImageView = itemView.findViewById(R.id.feed_item_book_cover)
        var book_date: TextView = itemView.findViewById(R.id.feed_item_book_date)
        var book_description: EditText = itemView.findViewById(R.id.feed_item_book_description)
        var cardView: CardView = itemView.findViewById(R.id.feed_item_book_cardView)

        fun setBookData(ct: Context, feedItem: FeedItem, dateFormat: SimpleDateFormat) {
            val BOOK_ADDED_PRETEXT = "Book added: "
            val BOOK_EDITED_PRETEXT = "Book edited: "

            if (feedItem.book.coverImage != null) {
                book_image.setImageBitmap(feedItem.book.coverImage)
            } else {
                book_image.setImageResource(R.drawable.no_cover_image)
            }
            book_title.text = feedItem.book.title

            if (feedItem.feedAction == FeedAction.BOOK_ADDED) {
                book_date.text =
                    "${BOOK_ADDED_PRETEXT} ${dateFormat.format(feedItem.date).toString()}"
            }

            book_description.setText(feedItem.book.description.toString())
        }
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val REVIEW_ADDED_PRETEXT = "Review written: "
        private val REVIEW_EDITED_PRETEXT = "Review edited: "


        var book_cover: ImageView = itemView.findViewById(R.id.myReview_cardView_book_image)
        var book_title: TextView = itemView.findViewById(R.id.myReviews_cardView_book_title)
        var review_date: TextView = itemView.findViewById(R.id.myReviews_cardView_date)
        var ratingBar: RatingBar = itemView.findViewById(R.id.myReviews_cardView_ratingBar)
        var review_text: EditText = itemView.findViewById(R.id.myReviews_cardView_review)
        var cardView: CardView = itemView.findViewById(R.id.myReviews_cardView)

        fun setReviewData(ct: Context, feedItem: FeedItem, dateFormat: SimpleDateFormat) {
            if (feedItem.review.book.coverImage == null) {
                book_cover.setImageResource(R.drawable.no_cover_image)
            } else {
                book_cover.setImageBitmap(feedItem.review.book.coverImage)
            }
            book_title.text = feedItem.review.book.title.toString()

            if(feedItem.feedAction == FeedAction.REVIEW_ADDED){
                review_date.text = REVIEW_ADDED_PRETEXT + " " + dateFormat.format(feedItem.review.date).toString()
            }else if(feedItem.feedAction == FeedAction.REVIEW_EDITED){
                review_date.text = REVIEW_EDITED_PRETEXT + " " + dateFormat.format(feedItem.review.date).toString()
            }
            ratingBar.rating = feedItem.review.rating
            review_text.setText(feedItem.review.comment.toString())
        }
    }
}