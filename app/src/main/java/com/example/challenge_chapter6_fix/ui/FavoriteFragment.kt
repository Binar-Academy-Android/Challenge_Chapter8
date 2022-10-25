package com.example.challenge_chapter6_fix.ui

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge_chapter6_fix.FavoriteModelFactory
import com.example.challenge_chapter6_fix.MainViewModelFactory
import com.example.challenge_chapter6_fix.R
import com.example.challenge_chapter6_fix.adapter.FavoriteMovieAdapter
import com.example.challenge_chapter6_fix.adapter.MovieAdapter
import com.example.challenge_chapter6_fix.data.dao.FavoriteDao
import com.example.challenge_chapter6_fix.databinding.FragmentFavoriteBinding
import com.example.challenge_chapter6_fix.model.Item
import com.example.challenge_chapter6_fix.service.ApiClient
import com.example.challenge_chapter6_fix.service.ApiHelper
import com.example.challenge_chapter6_fix.viewModel.FavoriteViewModel
import com.example.challenge_chapter6_fix.viewModel.MovieViewModel

class FavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]


        viewModel.getAllFavoriteMovie()
        viewModel.list_favorite.observe(viewLifecycleOwner){
            if(it == null){
                Toast.makeText(requireContext(), "Belum ada FILM Favorit", Toast.LENGTH_SHORT).show()
            }
            else {
//                adapter.setData(it.items as List<Item>)
                binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
                binding.rvPost.setHasFixedSize(false)
                binding.rvPost.adapter = FavoriteMovieAdapter(it)
            }
        }

        binding.btnBack.setOnClickListener(){
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}