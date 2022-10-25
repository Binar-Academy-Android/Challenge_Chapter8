package com.example.challenge_chapter6_fix.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.data.dao.FavoriteData
import com.example.challenge_chapter6_fix.databinding.ListFavBinding
import com.example.challenge_chapter6_fix.model.Item

class FavoriteMovieAdapter(private val listMovie: List<FavoriteData>) :
    RecyclerView.Adapter<FavoriteMovieAdapter.FavMovieViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Item>(){
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem.id == oldItem.id
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, differCallback)


    class FavMovieViewHolder(var binding: ListFavBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: FavoriteData) {
            with(itemView) {
                binding.apply {
                    title.text = movie.title
                    originalTitle.text = movie.originaltitle
                    Glide.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w400${movie.posterPath}")
                        .into(binding.imgFilm)
                    cardView.setOnClickListener{
                        val bundle = Bundle().apply {
                            putInt("ID", movie.id_fav.toString().toInt())
                        }
                        it.findNavController().navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
                    }
                }
            }

        }
    }

    fun setData(data : List<Item>){
        differ.submitList(data)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMovieAdapter.FavMovieViewHolder =
        FavMovieViewHolder(ListFavBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) =
        holder.bind(listMovie[position])


    override fun getItemCount(): Int = listMovie.size
}