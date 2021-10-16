package com.sunu.app.apptv.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunu.app.apptv.R
import com.sunu.app.apptv.common.Constatnts
import com.sunu.app.apptv.domain.model.Movies

class MoviesSearchAdapter(private val context: Context): RecyclerView.Adapter<MoviesSearchAdapter.MyViewHolder>() {
    var movieList = mutableListOf<Movies>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //get my movie
        val movie = movieList[position]
        holder.bind(movie,context)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtTitlemovie: TextView = itemView.findViewById<View>(R.id.txt_title) as TextView
        var txtSubtitle: TextView = itemView.findViewById<View>(R.id.txt_subtitle) as TextView
        var imgMovie: ImageView = itemView.findViewById<View>(R.id.img_movie) as ImageView

        fun bind(movie: Movies, context: Context){
            val title = movie.title[0]
            txtTitlemovie.text = title.value
            txtSubtitle.text = movie.subtitle
            Glide.with(context).load("${Constatnts.BASE_URL}${movie.imageurl}").placeholder(R.drawable.placeholder).fitCenter().into(imgMovie)
        }
    }
}