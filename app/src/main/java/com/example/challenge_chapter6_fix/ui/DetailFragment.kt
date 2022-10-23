package com.example.challenge_chapter6_fix.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        var getoriginalTitle = arguments?.getString("originalTitle")
        var gettitle = "Title : " + arguments?.getString("title")
        var getposterPath = arguments?.getString("posterPath")
        var getrelease = "Release : " + arguments?.getString("releaseDate")
        var getpopularity = "Popularity : " + arguments?.getDouble("popularity")
        var getlanguage = "Language : " + arguments?.getString("language")
        var getoverview = arguments?.getString("overview")

        binding.ivPoster.load("https://www.themoviedb.org/t/p/w220_and_h330_face/" + getposterPath){
            crossfade(true)
            placeholder(R.drawable.ic_baseline_menu_24)
        }

        binding.originalTitle.setText(getoriginalTitle)
        binding.title.setText(gettitle)
        binding.releaseDate.setText(getrelease)
        binding.popularity.setText(getpopularity)
        binding.language.setText(getlanguage)
        binding.overview.setText(getoverview)

        binding.btnBack.setOnClickListener(){
            findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
        }

        return binding.root
    }

}