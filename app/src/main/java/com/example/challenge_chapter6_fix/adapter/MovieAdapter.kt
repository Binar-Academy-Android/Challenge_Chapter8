package com.example.challenge_chapter6_fix.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.databinding.ListFilmBinding
import com.example.challenge_chapter6_fix.model.Item


class MovieAdapter (private val itemClick: (Item) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>(){

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

    class ViewHolder(private val binding: ListFilmBinding, val itemClick: (Item) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            with(item) {
                itemView.setOnClickListener { itemClick(this) }
                binding.dataBinding = item

                binding.imgFilm.load("https://image.tmdb.org/t/p/w500/" + item.backdropPath) {
                    crossfade(true)
                    placeholder(R.drawable.img_placeholder)
                }

                binding.card.setOnClickListener{
                    var bund = Bundle()
                    item.id?.let { it1 -> bund.putInt("id", it1) }
                    item.originalTitle?.let { it1 -> bund.putString("originalTitle", it1) }
                    item.title?.let { it1 -> bund.putString("title", it1) }
                    item.posterPath?.let { it1 -> bund.putString("posterPath", it1) }
                    item.releaseDate?.let { it1 -> bund.putString("releaseDate", it1) }
                    item.popularity?.let { it1 -> bund.putDouble("popularity", it1) }
                    item.originalLanguage?.let { it1 -> bund.putString("language", it1) }
                    item.overview?.let { it1 -> bund.putString("overview", it1) }
                    findNavController(it).navigate(R.id.action_homeFragment_to_detailFragment, bund)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val film = differ.currentList[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setData(data : List<Item>){
        differ.submitList(data)
    }

    interface ListMovieInterface {
        fun onItemClick(MovieDetail: Item)
    }
}