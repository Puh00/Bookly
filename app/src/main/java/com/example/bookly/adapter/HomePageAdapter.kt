package com.example.bookly.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.R

class HomePageAdapter(var ct: Context, private val s1: List<String>, private val s2: List<String>): RecyclerView.Adapter<HomePageAdapter.EventViewHolder>() {

    var context: Context = ct
    var data1: List<String> = s1
    var data2: List<String> = s2

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventViewHolder {
        var inflater: LayoutInflater = LayoutInflater.from(context)
        var view: View = inflater.inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data1.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.text1.text = data1[position]
        holder.text2.text = data2[position]
        holder.image.setImageResource(R.drawable.pekora_peko)
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text1: TextView = itemView.findViewById(R.id.myText1)
        var text2: TextView = itemView.findViewById(R.id.myText2)
        var image: ImageView = itemView.findViewById(R.id.programmingImage)
    }
}