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
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.R
import com.example.bookly.backend.BooklyDataHandler
import com.example.bookly.backend.Review
import java.text.SimpleDateFormat

class ReviewsAdapter(var ct: Context, var reviewData: List<Review>) :
    RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(ct)
        val view = inflater.inflate(R.layout.item_cardview_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.setReviewData(ct, reviewData[position])

        //Listeners
        holder.cardView.setOnClickListener { view: View ->
            BooklyDataHandler.getInstance().currentBookForReview = reviewData[position].book
            view.findNavController().navigate(R.id.action_myReviewsFragment_to_writeAReviewFragment)
        }
    }

    override fun getItemCount(): Int {
        return reviewData.size
    }

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val REVIEW_PRETEXT = "Review written: "

        @SuppressLint("SimpleDateFormat")
        private val timeFormat: SimpleDateFormat = SimpleDateFormat("MMM d, YYYY")

        var book_cover: ImageView = itemView.findViewById(R.id.myReview_cardView_book_image)
        var book_title: TextView = itemView.findViewById(R.id.myReviews_cardView_book_title)
        var review_date: TextView = itemView.findViewById(R.id.myReviews_cardView_date)
        var ratingBar: RatingBar = itemView.findViewById(R.id.myReviews_cardView_ratingBar)
        var review_text: EditText = itemView.findViewById(R.id.myReviews_cardView_review)
        var cardView: CardView = itemView.findViewById(R.id.myReviews_cardView)

        fun setReviewData(ct: Context, review: Review) {
            if (review.book.coverImage == null) {
                review.book.coverImage =
                    AppCompatResources.getDrawable(
                        ct,
                        R.drawable.no_cover_image
                    )
            }
            book_cover.setImageDrawable(review.book.coverImage)
            book_title.text = review.book.title.toString()
            review_date.text = REVIEW_PRETEXT + " " + timeFormat.format(review.date).toString()
            ratingBar.rating = review.rating
            review_text.setText(review.comment.toString())
        }
    }
}