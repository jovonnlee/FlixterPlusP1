package com.example.flixterplusp1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieRecyclerViewAdapter(
    private val movs: List<Movies>,
    private val mListener: OnListFragmentInteractionsListener?
)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_view, parent, false)
        return MovieViewHolder(view)

    }
    inner class MovieViewHolder(val mView: View) :RecyclerView.ViewHolder(mView) {
        var mItem: Movies? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movieTitle) as TextView
        val mMovieDescrip: TextView = mView.findViewById<View>(R.id.movieDescription) as  TextView
        val mPict: ImageView = mView.findViewById<View>(R.id.movieImage) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieDescrip.text + " '"
        }
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movie = movs[position]

        holder.mItem = movie
        holder.mMovieTitle.text = movie.movieTitle
        holder.mMovieDescrip.text = movie.movieDescription



        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/"+movie.movieImage)
            .centerInside()
            .into(holder.mPict)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie -> mListener?.onItemClick(movie)
            }
        }
    }
    override fun getItemCount(): Int {
        return movs.size
    }

}
